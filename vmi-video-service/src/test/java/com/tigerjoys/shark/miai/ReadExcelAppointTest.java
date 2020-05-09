package com.tigerjoys.shark.miai;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tigerjoys.nbs.common.utils.Tools;

public class ReadExcelAppointTest {

	public static void main(String[] args) {

		File file = new File("D:/xlsx");
		File[] fileList = file.listFiles();
		for (File re : fileList) {
			readExcel(re);
		}
	}

	public static void readExcel(File excel) {
		try {
			// String encoding = "GBK";
			if (excel.isFile() && excel.exists()) { // 判断文件是否存在

				String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
				Workbook wb;
				// 根据文件后缀（xls/xlsx）进行判断
				if ("xls".equals(split[1])) {
					FileInputStream fis = new FileInputStream(excel); // 文件流对象
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(excel);
				} else {
					System.out.println("文件类型错误!");
					return;
				}
				 int topType = 1;
				// 开始解析
				Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
			   
				int firstRowIndex = sheet.getFirstRowNum() + 1; // 第一行是列名，所以不读
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("firstRowIndex: " + firstRowIndex);
				System.out.println("lastRowIndex: " + lastRowIndex);
				System.out.println("------------------------------");
				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
					Row row = sheet.getRow(rIndex);
					if (row != null) {
						Cell useridCell = row.getCell(0);
						Cell parentCell = row.getCell(1);
						Cell subTypeCell = row.getCell(2);
						Cell titleCell = row.getCell(4);
						Cell addresCell = row.getCell(5);
						Cell createTimeCell = row.getCell(6);
						// String lng = sheet.getCell(7);
						// String lat = sheet.getCell(8);
						Cell descCell = row.getCell(9);
						
						String userid = (useridCell == null ? "" : useridCell.toString());
						String parent = (parentCell == null ? "" : parentCell.toString());
						String subType = (subTypeCell == null ? "" : subTypeCell.toString());
						String title = (titleCell == null ? "" : titleCell.toString());
						String addres = (addresCell == null ? "" : addresCell.toString());
						String createTime = (createTimeCell == null ? "" : createTimeCell.toString());
						String desc = (descCell == null ? "" : descCell.toString());
						
						String lng = "116.3196334839";
						String lat = "39.9879837036";
				         
				         System.err.println("INSERT INTO t_appoint ( create_time, update_time, refresh_time, userid, title, parent_type_id, appoint_type_id, address, appoint_time_id, description, cityid, lng, lat, STATUS) VALUES('"+createTime+"','"+createTime+"','"+createTime+"','"+userid+"','"+title+"','"+parent+"','"+subType+"','"+addres+"','0','"+desc+"','131','"+lng+"','"+lat+"','1');");
				 
					}
				}
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
