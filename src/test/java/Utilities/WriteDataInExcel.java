package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteDataInExcel {

	public static void setTheValueIntoCell(String status, int rowNum, int cellNum)

	{
		String filePath = "C://Users//admin//APIAutomation//KarateAPIFramework//src//test//resources//data//requirement1.xlsx";

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(rowNum - 1);

			if (row == null) {
				row = sheet.createRow(rowNum);
			}

			Cell cell = row.createCell(cellNum);
			cell.setCellValue(status);

			try (FileOutputStream fos = new FileOutputStream(filePath)) {
				workbook.write(fos);
			}

			System.out.println("Data written successfully.");
		} catch (IOException e) {
			System.out.println("Error writing data to Excel file: " + e.getMessage());
		}

	}

	public static String getTheValueFromCell(int rowno, int cellno) throws IOException {
		FileInputStream inputStream = new FileInputStream(
				"C://Users//admin//APIAutomation//KarateAPIFramework//src//test//resources//data//requirement1.xlsx");

		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet("requirement1");

		Row row = sheet.getRow(rowno); // Row 0 is the first row

		Cell cell = row.getCell(cellno); // Column 0 is the first column

		String value = String.valueOf(cell.getNumericCellValue());

		String[] afterSplitting = value.split("\\.");

		System.out.println(afterSplitting[0]);

		System.out.println(value);

		return afterSplitting[0];
	}

	public static void setTheStatusIntoExcelFile(String status, int rowNum) {
		System.out.println("Status is " + status);
		System.out.println(status + " is of type " + ((Object) status).getClass().getSimpleName());

		if (status.equals("200")) {
			System.out.println("In If block");
			setTheValueIntoCell("Pass", rowNum, 2);
		} else {
			System.out.println("In else block");
			setTheValueIntoCell("Fail", rowNum, 2);
		}
	}

	private static String getCellValue(Cell cell) {
		if (cell == null) {
			return ""; // Empty cell
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
		default:
			return "";
		}
	}

	public static List<String> readRowsFromExcel(int rowNum) throws IOException {
		FileInputStream inputStream = new FileInputStream(
				"C://Users//admin//APIAutomation//KarateAPIFramework//src//test//resources//data//requirement1.xlsx");

		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet("requirement1");

		List<String> rowData = new ArrayList<>();
		Row row = sheet.getRow(rowNum - 1);
		if (row != null) {
			for (Cell cell : row) {
				rowData.add(getCellValue(cell));
			}
		}

		workbook.close();
		inputStream.close();

		return rowData;
	}

	public static List setTheValuesFromExcelFileInFeatureFile(int rowNum) throws IOException {
		List<String> rowData = readRowsFromExcel(rowNum); // Read row 2
		// Print or process the rowData as needed
		System.out.println(rowData);

		rowData.get(0);
		String[] afterSplitting = rowData.get(0).split("\\.");

		System.out.println(afterSplitting[0]);

		String[] afterSplitting1 = rowData.get(1).split("\\.");

		System.out.println(afterSplitting1[0]);

		List<String> rowData1 = new ArrayList<>();
		rowData1.add(afterSplitting[0]);
		rowData1.add(afterSplitting1[0]);
		System.out.println(rowData1);
		return rowData1;

	}

	public static void main(String args[]) throws IOException {
		WriteDataInExcel w = new WriteDataInExcel();
		// w.setTheValueIntoCell("Pass");
		w.getTheValueFromCell(1, 1);
		//w.setThePageNumberAndStatusFromExcelFileInFeatureFile(2);

	}

}
