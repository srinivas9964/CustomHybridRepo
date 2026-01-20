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
}
