package com.w2a.API_Batch.testUtils;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.w2a.API_Batch.setUp.APISetUp;

import java.lang.reflect.Method;

public class DataProviders extends APISetUp {

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		/*
		 * { String sheetName = m.getName(); int rows = excel.getRowCount(sheetName);
		 * int cols = excel.getColumnCount(sheetName); Object[][] data = new
		 * Object[rows-1][1]; Hashtable<String, String> table = null; for (int rowNum =
		 * 2;rowNum <= rows;rowNum++) { table= new Hashtable<String, String>(); for (int
		 * colNum = 0; colNum < cols; colNum++) { table.put(excel.getCellData(sheetName,
		 * colNum, 1), excel.getCellData(sheetName, colNum, rowNum)); data[rowNum-2][0]
		 * = table; } }
		 * 
		 * 
		 * return data;
		 * 
		 * 
		 * }
		 */

		System.out.println(configProperty);
		System.out.println("SheetName-->" + configProperty.getTestDataSheetName());
		String sheetName = configProperty.getTestDataSheetName();
		int rows = excel.getRowCount(sheetName);
		String testName = m.getName();
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
			String testCaseName = excel.getCellData(sheetName, 0, testCaseRowNum);
			if (testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;
		while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("endOfTestData")) {

			testRows++;

		}

		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;

		while (!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {

			testCols++;

		}

		Object[][] data = new Object[testRows][1];

		int i = 0;

		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < testCols; cNum++) {

				String colName = excel.getCellData(sheetName, cNum, colStartColNum);
				String testData = excel.getCellData(sheetName, cNum, rNum);

				table.put(colName, testData);
			}
			
			data[i][0] = table;
			i++;
		}
		
		return data;
	}

}
