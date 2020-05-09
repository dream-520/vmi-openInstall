package com.tigerjoys.shark.miai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class AppointTest {
	
public static void main(String[] args) {
	
	File file=new File("D:/xlsx");
	File[] fileList=file.listFiles();
	for(File re:fileList){
			readExcel(re);
	}
}

public static int readExcel(File ifilepath){
		  Workbook workbook = null;
		  try {
		    workbook = workbook.getWorkbook(ifilepath);
		    int sheetCount = workbook.getNumberOfSheets();
		    int count = 0;
		    for (int index = 0; index < sheetCount; index++) {
		      Sheet sheet = workbook.getSheet(index);
		      int rows = sheet.getRows();
		      int cols = sheet.getColumns();
		 

		      if (cols == 10) {
		        for (int i = 1; i < rows; i++) {
		          String userid = sheet.getCell(0, i).getContents();
		          String parent = sheet.getCell(1, i).getContents();
		          String subType = sheet.getCell(2, i).getContents();
		          String title = sheet.getCell(4, i).getContents();
		          String addres = sheet.getCell(5, i).getContents();
		          String createTime = sheet.getCell(6, i).getContents();
		         // String lng = sheet.getCell(7, i).getContents();
		         // String lat = sheet.getCell(8, i).getContents();
		          String desc = sheet.getCell(9, i).getContents();
		          
		           String lng = "116.3196334839";
			       String lat = "39.9879837036";
		         
		         System.err.println("INSERT INTO t_appoint ( create_time, update_time, refresh_time, userid, title, parent_type_id, appoint_type_id, address, appoint_time_id, description, cityid, lng, lat, STATUS) VALUES('"+createTime+"','"+createTime+"','"+createTime+"','"+userid+"','"+title+"','"+parent+"','"+subType+"','"+addres+"','0','"+desc+"','131','"+lng+"','"+lat+"','1');");
		 
		         // System.out.println(userid+" "+lng+" "+lat+"  "+desc);
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
		  
		
		  } catch (BiffException e) {
			  e.printStackTrace();
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
