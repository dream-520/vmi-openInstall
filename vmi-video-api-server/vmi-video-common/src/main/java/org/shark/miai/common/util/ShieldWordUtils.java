package org.shark.miai.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 脏词过滤工具类
 * @author chengang
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public final class ShieldWordUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShieldWordUtils.class);
	
	/**
	 * 最小匹配规则
	 */
	public static final int MIN_MATCH_TYPE = 1;
	
	/**
	 * 最大匹配规则
	 */
	public static final int MAX_MATCH_TYPE = 2;
	
	/**
	 * 缓存敏感词
	 */
	private static Map SENSITIVE_WORD_MAP = null;
	
	static {
		reloadSensitiveWord();
	}

	/**
	 * 判断文本中是否包含过滤词
	 * @param txt - 要匹配的文本
	 * @return boolean
	 */
	public boolean containsSensitiveWord(String txt) {
		boolean flag = false;
		for(int i = 0 ; i < txt.length() ; i++){
			int matchFlag = checkSensitiveWord(txt, i, MIN_MATCH_TYPE);//判断是否包含敏感字符
			if(matchFlag > 0){//大于0存在，返回true
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 匹配文本中包含的过滤词，默认按照最小匹配规则(匹配到最短的单词即可)
	 * @param txt - 要匹配的文本
	 * @return Set<String>
	 */
	public static Set<String> getSensitiveWord(String txt) {
		return getSensitiveWord(txt , MIN_MATCH_TYPE);
	}

	/**
	 * 匹配文本中包含的过滤词
	 * @param txt - 要匹配的文本
	 * @param matchType - 匹配规则
	 * @return Set<String>
	 */
	public static Set<String> getSensitiveWord(String txt, int matchType) {
		Set<String> sensitiveWordList = new HashSet<String>();
		
		for(int i = 0 ; i < txt.length() ; i++){
			int length = checkSensitiveWord(txt, i, matchType);//判断是否包含敏感字符
			if(length > 0){//存在,加入list中
				sensitiveWordList.add(txt.substring(i, i+length));
				i = i + length - 1;//减1的原因，是因为for会自增
			}
		}
		
		return sensitiveWordList;
	}

	/**
	 * 将文本中包含过滤词替换为指定的字符
	 * @param txt - 要匹配的文本
	 * @param matchType - 匹配规则
	 * @param replaceChar - 替换的字符
	 * @return String
	 */
	public static String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
		String resultTxt = txt;
		Set<String> set = getSensitiveWord(txt, matchType);     //获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}
		
		return resultTxt;
	}
	
	/**
	 * 获取替换字符串
	 * @param replaceChar - 字符字符
	 * @param length
	 * @return
	 */
	private static String getReplaceChars(String replaceChar,int length){
		String resultReplace = replaceChar;
		for(int i = 1 ; i < length ; i++){
			resultReplace += replaceChar;
		}
		
		return resultReplace;
	}
	
	/**
	 * 检查文字中是否包含敏感字符，检查规则如下：
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return，如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	private static int checkSensitiveWord(String txt,int beginIndex,int matchType){
		boolean flag = false;//敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;//匹配标识数默认为0
		char word = 0;
		Map nowMap = SENSITIVE_WORD_MAP;
		for(int i = beginIndex; i < txt.length() ; i++){
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word);//获取指定key
			if(nowMap != null){//存在，则判断是否为最后一个
				matchFlag++;//找到相应key，匹配标识+1 
				if("1".equals(nowMap.get("isEnd"))){//如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true;       //结束标志位为true   
					if(MIN_MATCH_TYPE == matchType){//最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			}
			else{//不存在，直接返回
				break;
			}
		}
		if(matchFlag < 2 || !flag){//长度必须大于等于1，为词 
			matchFlag = 0;
		}
		return matchFlag;
	}
	
	/**
	 * 读取敏感词库，构建一个DFA算法模型：<br>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @return Map
	 * @throws Exception 
	 */
	private static Map addSensitiveWordToHashMap() throws Exception {
		Set<String> wordSet = loadShieldWordFile();
		if(Tools.isNull(wordSet)) {
			return new HashMap();
		}
		
		Map sensitiveWordMap = new HashMap(wordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = wordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);//转换成char型
				Object wordMap = nowMap.get(keyChar);//获取
				
				if(wordMap != null){//如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{//不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");//不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");//最后一个
				}
			}
		}
		
		return sensitiveWordMap;
	}
	
	/**
	 * 从文件中读取过滤词
	 * @return Set<String>
	 * @throws IOException
	 */
	private static Set<String> loadShieldWordFile() throws IOException{
		Resource fileRource = new ClassPathResource("shield_word.txt");
		if(!fileRource.exists()) {
			LOGGER.warn("shield_word.txt not exists!");
			return null;
		}
		
		long stime = System.currentTimeMillis();
		Set<String> wordSet = new HashSet<String>();
		try(FileReader fr = new FileReader(fileRource.getFile());BufferedReader br = new BufferedReader(fr);) {
			String s = null;
			while((s = br.readLine()) != null) {
				s = s.trim();
				if(s.length() > 0) {
					wordSet.add(s);
				}
			}
		}
		
		LOGGER.info("读取shield_word.txt耗时：" + (System.currentTimeMillis() - stime) + "毫秒!");
		return wordSet;
	}

	/**
	 * 重新加载过滤词
	 */
	public static void reloadSensitiveWord() {
		Map sensitiveWordMap = null;
		try {
			sensitiveWordMap = addSensitiveWordToHashMap();
		} catch (Exception e) {
			e.printStackTrace();
			
			sensitiveWordMap = new HashMap<>();
		}
		
		SENSITIVE_WORD_MAP = sensitiveWordMap;
		LOGGER.info("敏感词的数量：" + sensitiveWordMap.size());
	}

}
