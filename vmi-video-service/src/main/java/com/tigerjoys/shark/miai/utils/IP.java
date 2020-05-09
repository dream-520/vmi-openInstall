package com.tigerjoys.shark.miai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 根据IP地址获取城市信息
 * @author chengang
 *
 */
public class IP {
	
	private static final Log logger = LogFactory.getLog(IP.class);

    public static String randomIp() {
        Random r = new Random();
        StringBuffer str = new StringBuffer();
        str.append(r.nextInt(1000000) % 255);
        str.append(".");
        str.append(r.nextInt(1000000) % 255);
        str.append(".");
        str.append(r.nextInt(1000000) % 255);
        str.append(".");
        str.append(0);

        return str.toString();
    }

    private static int offset;
    private static int[] index = new int[256];
    private static ByteBuffer dataBuffer;
    private static ByteBuffer indexBuffer;
    private static ReentrantLock lock = new ReentrantLock();
    
    static {
    	IP.init();
    }

    private static void init() {
    	logger.info("初始化IP库....开始");
    	long st = System.currentTimeMillis();
        load();
        long end = System.currentTimeMillis();
        logger.info("初始化IP数据完成....费时==="+(end-st)+"毫秒...");
    }

    private static String[] find(String ip) {
        int ip_prefix_value = new Integer(ip.substring(0, ip.indexOf(".")));
        long ip2long_value  = ip2long(ip);
        int start = index[ip_prefix_value];
        int max_comp_len = offset - 1028;
        long index_offset = -1;
        int index_length = -1;
        byte b = 0;
        for (start = start * 8 + 1024; start < max_comp_len; start += 8) {
            if (int2long(indexBuffer.getInt(start)) >= ip2long_value) {
                index_offset = bytesToLong(b, indexBuffer.get(start + 6), indexBuffer.get(start + 5), indexBuffer.get(start + 4));
                index_length = 0xFF & indexBuffer.get(start + 7);
                break;
            }
        }

        byte[] areaBytes;

        lock.lock();
        try {
            dataBuffer.position(offset + (int) index_offset - 1024);
            areaBytes = new byte[index_length];
            dataBuffer.get(areaBytes, 0, index_length);
        } finally {
            lock.unlock();
        }

        return new String(areaBytes, Charset.forName("UTF-8")).split("\t", -1);
    }
    
    /**
     * 根据IP地址找到国家，省份，城市，区域，其中项目可能为空，请注意判断
     * @param ip - IP地址
     * @return String[]
     */
    public static String[] findArea(String ip) {
    	String[] basicArray = find(ip);
    	if(basicArray == null || basicArray.length < 3) {
    		return null;
    	}
    	
    	int size = 3;
    	String[] area = new String[size+1];
    	for(int i=0;i<size;i++) {
    		area[i] = basicArray[i];
    	}
    	//判断最后的区域是否存在，如果存在则赋值
    	if(basicArray.length == size+1) {
    		area[size] = basicArray[size];
    	}
    	
    	return area;
    }

    private static void load() {
        InputStream fin = null;
        lock.lock();
        try {
            fin = new ClassPathResource("ip/mydata4vipday2.dat").getInputStream();
            dataBuffer = ByteBuffer.allocate(fin.available());
            int readBytesLength;
            byte[] chunk = new byte[4096];
            while (fin.available() > 0) {
                readBytesLength = fin.read(chunk);
                dataBuffer.put(chunk, 0, readBytesLength);
            }
            dataBuffer.position(0);
            int indexLength = dataBuffer.getInt();
            byte[] indexBytes = new byte[indexLength];
            dataBuffer.get(indexBytes, 0, indexLength - 4);
            indexBuffer = ByteBuffer.wrap(indexBytes);
            indexBuffer.order(ByteOrder.LITTLE_ENDIAN);
            offset = indexLength;

            int loop = 0;
            while (loop++ < 256) {
                index[loop - 1] = indexBuffer.getInt();
            }
            indexBuffer.order(ByteOrder.BIG_ENDIAN);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    private static long bytesToLong(byte a, byte b, byte c, byte d) {
        return int2long((((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff)));
    }

    private static int str2Ip(String ip)  {
        String[] ss = ip.split("\\.");
        int a, b, c, d;
        a = Integer.parseInt(ss[0]);
        b = Integer.parseInt(ss[1]);
        c = Integer.parseInt(ss[2]);
        d = Integer.parseInt(ss[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    private static long ip2long(String ip)  {
        return int2long(str2Ip(ip));
    }

    private static long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }

    public static void main(String[] args){
        /*Long st = System.nanoTime();
        for (int i = 0; i < 1000000; i++)
        {
            IP.find(randomIp());
        }
        Long et = System.nanoTime();
        System.out.println((et - st) / 1000 / 1000);*/

    	String ip = "118.182.67.111";
    	String[] area = IP.findArea(ip);
        System.out.println(Arrays.toString(IP.findArea(ip)));
        System.err.println(IP.findArea(ip).length);
    }
    
}
