package com.tigerjoys.shark.miai.configuration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.logs.HitLogger;
import com.tigerjoys.nbs.web.utils.KeyFilterUtile;

import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.TypeUtils;

/**
 * 加解密的Json解析器
 * @author chengang
 *
 */
@SuppressWarnings("unused")
public class AESJackson2HttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {

	/**
	 * The default charset used by the converter.
	 */
	@Nullable
	@Deprecated
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


	protected ObjectMapper objectMapper;

	@Nullable
	private Boolean prettyPrint;

	@Nullable
	private PrettyPrinter ssePrettyPrinter;


	protected AESJackson2HttpMessageConverter(ObjectMapper objectMapper) {
		super(new MediaType("text", "encode", DEFAULT_CHARSET),new MediaType("text", "*+encode", DEFAULT_CHARSET));
		
		this.objectMapper = objectMapper;
		DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
		prettyPrinter.indentObjectsWith(new DefaultIndenter("  ", "\ndata:"));
		this.ssePrettyPrinter = prettyPrinter;
	}

	protected AESJackson2HttpMessageConverter(ObjectMapper objectMapper, MediaType supportedMediaType) {
		this(objectMapper);
		setSupportedMediaTypes(Collections.singletonList(supportedMediaType));
	}

	protected AESJackson2HttpMessageConverter(ObjectMapper objectMapper, MediaType... supportedMediaTypes) {
		this(objectMapper);
		setSupportedMediaTypes(Arrays.asList(supportedMediaTypes));
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "ObjectMapper must not be null");
		this.objectMapper = objectMapper;
		configurePrettyPrint();
	}

	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
		configurePrettyPrint();
	}

	private void configurePrettyPrint() {
		if (this.prettyPrint != null) {
			this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint);
		}
	}

	@Override
	public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
		return canRead(clazz, null, mediaType);
	}

	@Override
	public boolean canRead(Type type, @Nullable Class<?> contextClass, @Nullable MediaType mediaType) {
		if (!canRead(mediaType)) {
			return false;
		}
		JavaType javaType = getJavaType(type, contextClass);
		AtomicReference<Throwable> causeRef = new AtomicReference<>();
		if (this.objectMapper.canDeserialize(javaType, causeRef)) {
			return true;
		}
		logWarningIfNecessary(javaType, causeRef.get());
		return false;
	}

	@Override
	public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
		if (!canWrite(mediaType)) {
			return false;
		}
		AtomicReference<Throwable> causeRef = new AtomicReference<>();
		if (this.objectMapper.canSerialize(clazz, causeRef)) {
			return true;
		}
		logWarningIfNecessary(clazz, causeRef.get());
		return false;
	}

	/**
	 * Determine whether to log the given exception coming from a
	 * {@link ObjectMapper#canDeserialize} / {@link ObjectMapper#canSerialize} check.
	 * @param type the class that Jackson tested for (de-)serializability
	 * @param cause the Jackson-thrown exception to evaluate
	 * (typically a {@link JsonMappingException})
	 * @since 4.3
	 */
	protected void logWarningIfNecessary(Type type, @Nullable Throwable cause) {
		if (cause == null) {
			return;
		}

		// Do not log warning for serializer not found (note: different message wording on Jackson 2.9)
		boolean debugLevel = (cause instanceof JsonMappingException && cause.getMessage().startsWith("Cannot find"));

		if (debugLevel ? logger.isDebugEnabled() : logger.isWarnEnabled()) {
			String msg = "Failed to evaluate Jackson " + (type instanceof JavaType ? "de" : "") +
					"serialization for type [" + type + "]";
			if (debugLevel) {
				logger.debug(msg, cause);
			}
			else if (logger.isDebugEnabled()) {
				logger.warn(msg, cause);
			}
			else {
				logger.warn(msg + ": " + cause);
			}
		}
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(clazz, null);
		return readJavaType(javaType, inputMessage);
	}

	@Override
	public Object read(Type type, @Nullable Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(type, contextClass);
		return readJavaType(javaType, inputMessage);
	}

	private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) throws IOException {
		try {
			Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());
			String str = StreamUtils.copyToString(inputMessage.getBody(), charset);
			
			try {
				if(str != null) {
					str = str.trim();
				}
				str = AESCipher.aesDecryptString(str);
				str = Tools.cleanXSS(str);
			} catch (Exception e) {
				logger.info("解密获取的数据发生异常!!!");
				str = null;
			}
			
			BeatContext beat = RequestUtils.getCurrent();
			beat.setRequestBody(str);
			beat.setEncode(true);
			
			//记录点击日志
			if(beat.isIslog()) HitLogger.writerHitLog();
			if(str != null) {
//				logger.info("获取数据解密后："+str);
				
				//过滤一些特殊字符
				str = str.replace("\\0", "");
				if(javaType.getRawClass() == String.class) {
					return str;
				} else {
					return this.objectMapper.readValue(str, javaType);
				}
			}
			
			return "{}";
		}
		catch (InvalidDefinitionException ex) {
			throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotReadableException("JSON parse error: " + ex.getOriginalMessage(), ex, inputMessage);
		}
	}

	@Override
	protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		try {
			Class<?> clazz = object.getClass();
			String str = null;
			if(clazz == String.class || clazz == StringBuilder.class || clazz == StringBuffer.class) {
				str = String.valueOf(object);
			} else {
				str = this.objectMapper.writeValueAsString(object);
			}
			RequestHeader header =RequestUtils.getCurrent().getHeader();
			int osType = 0;
			if(Tools.isNotNull(header)){
				osType = RequestUtils.getCurrent().getHeader().getOs_type();
			}
			
			if(osType == 2){
				str = KeyFilterUtile.keyFilter(str);
			}
			HitLogger.writerReturnLog(HitLogger.RETURN_INFO_LOG , str);
			//logger.info("返回数据加密前："+str);
			try {
				str = AESCipher.aesEncryptString(str);//加密后的字符串
			} catch (Exception e) {
				logger.info("返回数据加密的时候发生异常!!!");
				
				str = null;
			}
			if(str != null) {
				//logger.info("返回数据加密后："+str);
				JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), JsonEncoding.UTF8);
				//this.objectMapper.writeValue(generator, str);
				generator.writeRaw(str);
				generator.flush();
			}
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write content: " + ex.getMessage(), ex);
		}
	}

	/**
	 * Write a prefix before the main content.
	 * @param generator the generator to use for writing content.
	 * @param object the object to write to the output message.
	 */
	protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
	}

	/**
	 * Write a suffix after the main content.
	 * @param generator the generator to use for writing content.
	 * @param object the object to write to the output message.
	 */
	protected void writeSuffix(JsonGenerator generator, Object object) throws IOException {
	}

	/**
	 * Return the Jackson {@link JavaType} for the specified type and context class.
	 * @param type the generic type to return the Jackson JavaType for
	 * @param contextClass a context class for the target type, for example a class
	 * in which the target type appears in a method signature (can be {@code null})
	 * @return the Jackson JavaType
	 */
	protected JavaType getJavaType(Type type, @Nullable Class<?> contextClass) {
		TypeFactory typeFactory = this.objectMapper.getTypeFactory();
		return typeFactory.constructType(GenericTypeResolver.resolveType(type, contextClass));
	}

	/**
	 * Determine the JSON encoding to use for the given content type.
	 * @param contentType the media type as requested by the caller
	 * @return the JSON encoding to use (never {@code null})
	 */
	protected JsonEncoding getJsonEncoding(@Nullable MediaType contentType) {
		if (contentType != null && contentType.getCharset() != null) {
			Charset charset = contentType.getCharset();
			for (JsonEncoding encoding : JsonEncoding.values()) {
				if (charset.name().equals(encoding.getJavaName())) {
					return encoding;
				}
			}
		}
		return JsonEncoding.UTF8;
	}

	@Override
	@Nullable
	protected MediaType getDefaultContentType(Object object) throws IOException {
		if (object instanceof MappingJacksonValue) {
			object = ((MappingJacksonValue) object).getValue();
		}
		return super.getDefaultContentType(object);
	}

	@Override
	protected Long getContentLength(Object object, @Nullable MediaType contentType) throws IOException {
		if (object instanceof MappingJacksonValue) {
			object = ((MappingJacksonValue) object).getValue();
		} else if(object instanceof String) {
			Charset charset = getContentTypeCharset(contentType);
			try {
				return (long) String.valueOf(object).getBytes(charset.name()).length;
			}
			catch (UnsupportedEncodingException ex) {
				throw new IllegalStateException(ex);
			}
		}
		return super.getContentLength(object, contentType);
	}
	
	private Charset getContentTypeCharset(MediaType contentType) {
		if (contentType != null && contentType.getCharset() != null) {
			return contentType.getCharset();
		}
		else {
			return DEFAULT_CHARSET;
		}
	}

}
