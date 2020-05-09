package com.tigerjoys.shark.miai.font;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class WoffParser {

	@SuppressWarnings("serial")
	private static final LinkedHashMap<String, Integer> woffHeaderFormat = new LinkedHashMap<String, Integer>() {
		{
			put("signature", 4);
			put("flavor", 4);
			put("length", 4);
			put("numTables", 2);
			put("reserved", 2);
			put("totalSfntSize", 4);
			put("majorVersion", 2);
			put("minorVersion", 2);
			put("metaOffset", 4);
			put("metaLength", 4);
			put("metaOrigLength", 4);
			put("privOffset", 4);
			put("privOrigLength", 4);
		}
	};

	@SuppressWarnings("serial")
	private static final LinkedHashMap<String, Integer> tableRecordEntryFormat = new LinkedHashMap<String, Integer>() {
		{
			put("tag", 4);
			put("offset", 4);
			put("compLength", 4);
			put("origLength", 4);
			put("origChecksum", 4);
		}
	};

	private HashMap<String, Number> woffHeaders = new HashMap<String, Number>();

	private ArrayList<HashMap<String, Number>> tableRecordEntries = new ArrayList<HashMap<String, Number>>();

	private int offset = 0;

	private int readOffset = 0;

	private void getHeaders(DataInputStream woffFileStream) throws IOException {
		readTableData(woffFileStream, woffHeaderFormat, woffHeaders);
	}
	
    private void getTableRecordEntries(DataInputStream woffFileStream) throws IOException {
        int numTables = (Integer) woffHeaders.get("numTables");
        for (int i = 0; i < numTables; i++) {
            HashMap<String, Number> tableDirectory = new HashMap<String, Number>();
            readTableData(woffFileStream, tableRecordEntryFormat, tableDirectory);
            offset += 16;
            tableRecordEntries.add(tableDirectory);
        }
        for (HashMap<String, Number> tableRecordEntry : tableRecordEntries) {
        	tableRecordEntry.put("outOffset", offset);
        	offset += (Integer) tableRecordEntry.get("origLength");
            if (offset % 4 != 0) {
                offset += 4 - (offset % 4);
            }
        }
    }
    
    private void getTableRecordEntriesData(DataInputStream woffFileStream) throws Exception {
        for (HashMap<String, Number> tableRecordEntry : tableRecordEntries) {
            int tableRecordEntryOffset = (Integer) tableRecordEntry.get("offset");
            int skipBytes = tableRecordEntryOffset - readOffset;
            if (skipBytes > 0)
                woffFileStream.skip(skipBytes);
            readOffset += skipBytes;
            int compressedLength = (Integer) tableRecordEntry.get("compLength");
            int origLength = (Integer) tableRecordEntry.get("origLength");
            byte[] fontData = new byte[compressedLength];
            byte[] inflatedFontData = new byte[origLength];
            int readBytes = 0;
            while (readBytes < compressedLength) {
                readBytes += woffFileStream.read(fontData, readBytes, compressedLength - readBytes);
            }
            readOffset += compressedLength;
            inflatedFontData = inflateFontData(compressedLength, origLength, fontData, inflatedFontData);
            System.err.println(inflatedFontData.length);
            System.err.println(new String(inflatedFontData));
            
            offset = (Integer) tableRecordEntry.get("outOffset") + (Integer) tableRecordEntry.get("origLength");
            int padding = 0;
            if (offset % 4 != 0)
                padding = 4 - (offset % 4);
        }
    }
    
    private byte[] inflateFontData(int compressedLength, int origLength, byte[] fontData, byte[] inflatedFontData) throws Exception {
    	if (compressedLength != origLength) {
    		Inflater decompressor = new Inflater();
    		decompressor.setInput(fontData, 0, compressedLength);
    		try {
    			decompressor.inflate(inflatedFontData, 0, origLength);
    		} catch (DataFormatException e) {
    			throw new Exception("Malformed woff file");
    		}
    	} else
    		inflatedFontData = fontData;
    	return inflatedFontData;
    }

	private void readTableData(DataInputStream woffFileStream, LinkedHashMap<String, Integer> formatTable, HashMap<String, Number> table) throws IOException {
		Iterator<String> headerKeys = formatTable.keySet().iterator();
		while (headerKeys.hasNext()) {
			String key = headerKeys.next();
			int size = formatTable.get(key);
			if (size == 2) {
				table.put(key, woffFileStream.readUnsignedShort());
			} else if (size == 4) {
				table.put(key, woffFileStream.readInt());
			}
			readOffset += size;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.err.println("测试");
		WoffParser parser = new WoffParser();
		DataInputStream woffFileStream = new DataInputStream(new FileInputStream("E:\\woff\\tyc-num-832854095c.woff"));
		parser.getHeaders(woffFileStream);
		System.err.println(parser.woffHeaders.toString());
		if ((Integer) parser.woffHeaders.get("signature") != 0x774F4646) {
            throw new Exception("文件类型不正确");
        }
		parser.getTableRecordEntries(woffFileStream);
		System.err.println(parser.tableRecordEntries.toString());
		parser.getTableRecordEntriesData(woffFileStream);
		
	}
	
}
