package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ExcelUtils {
	public static List<Map<String, String>> readLoginSheetSheetData(String path, String sheetName) throws Exception {
		try {
			List<Map<String, String>> rows = new ArrayList<>();
			try (FileInputStream fis = new FileInputStream(path); 
			XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			Sheet sheet = wb.getSheet(sheetName);
			Row header = sheet.getRow(0);
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null) continue;
			
			Map<String, String> map = new HashMap<>();
			
			for (int c = 0; c < header.getLastCellNum(); c++) {
			Cell h = header.getCell(c);
			Cell cell = row.getCell(c);
			String key = h.getStringCellValue();
			String val = (cell == null) ? "" : cell.toString();
			map.put(key, val);
			}
			rows.add(map);
			}
			}
			return rows;	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		return null;
	}
	
	public static List<Map<String, String>> readPIMAddEmployeeData(String path, String sheetName)  {
		List<Map<String, String>> rows = new ArrayList<>();
		try {
			FileInputStream input = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			Sheet sheet = workbook.getSheet(sheetName);
			Row header = sheet.getRow(0);
			for(int r=1; r<=sheet.getLastRowNum(); r++) {
				Row row =sheet.getRow(r);
				if(row == null) continue;
				Map<String, String> map = new HashMap<>();
				for(int val=0; val<header.getLastCellNum(); val++) {
					Cell labelValue = header.getCell(val);
					Cell cellValue= row.getCell(val);
					String key = labelValue.getStringCellValue();
					String value = (cellValue == null) ? "" : cellValue.toString();
					map.put(key, value);	
				}
				rows.add(map);
			}
			return rows;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
}
