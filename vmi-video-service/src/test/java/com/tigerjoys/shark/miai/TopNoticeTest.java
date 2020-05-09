package com.tigerjoys.shark.miai;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.tigerjoys.nbs.common.utils.Tools;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;






public class TopNoticeTest {
	
public static void main(String[] args) {
	
	File file=new File("D:/xlsx");
	File[] fileList=file.listFiles();
	for(File re:fileList){
			try {
				readExcel(re);
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

public static int readExcel(File ifilepath) throws BiffException{
		  Workbook workbook = null;
		  try {
		    workbook = workbook.getWorkbook(ifilepath);
		    int sheetCount = workbook.getNumberOfSheets();

		    int topType = 1;
		    
		    int count = 0;
		    for (int index = 0; index < sheetCount; index++) {
		      Sheet sheet = workbook.getSheet(index);
		      int rows = sheet.getRows();
		      int cols = sheet.getColumns();
		 

		      if (cols == 2) {
		        for (int i = 1; i < rows; i++) {
		          String name = sheet.getCell(0, i).getContents();
		          String info = sheet.getCell(1, i).getContents();
		          name=(name==null?"":name.trim());
		          info=(info==null?"":info.trim());
		          System.out.println("INSERT INTO t_top_head_info(name,info,status,top_type,create_time) VALUES('"+name+"',"+"'"+info+"',"+1+","+topType+","+"'"+Tools.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"');");
		 
		         // */
		        }
		        
		      
		      
		  
		      }
		    }
		//  wwb.write();
			 
			  System.out.println("读取文件完毕");
		    return 1;
		  } catch (FileNotFoundException fe) {
			  fe.printStackTrace();
			  System.out.println("读取文件-->" + ifilepath + "时出错");
		    return 0;
		  } catch (IOException ie) {
			  ie.printStackTrace();
			  System.out.println("读取文件-->" + ifilepath + "时出错");
		    return 0;
		  
		
		  } finally {
		    if (workbook != null) {
		      workbook.close();
		    }
		   
		  }
		}
		  
		  public static boolean isEmpty(String param)
		  {
		    return ("".equals(param)) || (param == null);
		  }

		  public static boolean isEmpty(List list)
		  {
		    return (list == null) || (list.size() < 0);
		  }



}
