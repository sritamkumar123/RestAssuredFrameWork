package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	  public static Object[][] getExcelData(String filePath, String sheetName) {
	        Object[][] data = null;

	        try (FileInputStream fis = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	            data = new Object[rowCount - 1][colCount];

	            Iterator<Row> rowIterator = sheet.iterator();
	            rowIterator.next(); // Skip header row

	            int i = 0;
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    if (cell == null) {
	                        data[i][j] = ""; // Set empty string for null cells
	                    } else {
	                        data[i][j] = cell.toString();
	                    }
	                }
	                i++;
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return data;
	    }
	

}
