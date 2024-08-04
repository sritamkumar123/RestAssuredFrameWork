package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	   @DataProvider(name = "excelData")
	    public Object[][] excelDataProvider() {
	        String filePath = "..\\RestAssuredFrameWork\\TestData\\sampleFile.xlsx";
	        String sheetName = "Sheet1";

	        return ExcelUtils.getExcelData(filePath, sheetName);
	    }


}
