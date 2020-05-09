package org.shark.miai.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码工具类
 *
 */
public class QRCodeUtil {
	
	/**
	 * 得到内容的二维码的base64加密串
	 * @param content - 二维码内容
	 * @return String
	 */
	public static String getBase64Str(String content) {
		return Base64Crypto.encode(getByteArray(content));
	}
	
	/**
	 * 用原生的google的包得到内容的二维码字节码
	 * @param content - 二维码内容
	 * @return byte[]
	 */
	public static byte[] getByteArray(String content) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 250, 250, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create QR image from text due to underlying exception", e);
		}
		byte[] result = stream.toByteArray();
		try {stream.close();} catch (IOException e) {e.printStackTrace();} finally {stream = null;}
		return result;
	}

}
