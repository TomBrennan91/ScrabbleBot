package com.tutorialspoint;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ReportSheet implements Constants {
	
	int period;
	int year;
	int yearMod;
	boolean budget;
	boolean forecast;
	String currency;
	String clientName;
	public  HashMap<String, Integer> groupingMap;
	SXSSFSheet sheet;
	String reportType;
	boolean balanceSheet;
	
	public ReportSheet(String sheetName, SXSSFWorkbook wb, int period, int year, boolean budget, boolean forecast, String currency, String clientName, ArrayList<ReportRow> reportRows, Database db, int yearMod, String reportType) {
		sheet = ExcelFormatting.initialiseReportSheet(wb, sheetName);
		this.period = period;
		this.year = year + yearMod;
		this.budget = budget;
		this.currency = currency;
		this.clientName = clientName;
		this.yearMod = yearMod;
		this.forecast = forecast;
		this.reportType = reportType;
		this.balanceSheet = (reportType == "Balance Sheet");		
		writeReport(reportRows, db);
	}
	public void writeLine1(){
		//first into header displaying client name
		Cell current = sheet.createRow(0).createCell(1);
		current.setCellValue(clientName + " Group Management Accounts");
		current.setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_STYLE);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, COL_CUMULATIVE));
	}
	public void writeLine2(){
		// second intro header displaying sheetname
		Cell current = sheet.createRow(1).createCell(1);
		current.setCellValue(sheet.getSheetName());
		current.setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_STYLE);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, COL_CUMULATIVE));
	}	
	public void writeLine4(){
		//row 4 with the month names
		Row row = sheet.createRow(3);
		Cell current = row.createCell(1);
		current.setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_BOX_STYLE);
		for (int col = COL_HEADINGS; col <=COL_CUMULATIVE; col ++){
			current = row.createCell(col);
			current.setCellValue( getShortMonthbyPeriod(col-2) +" '"+ (year % 100));
			current.setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_BOX_STYLE);
		}
	}
	private void writeLine7() {
		Row row = sheet.createRow(ROW_HEADINGS);
		for (int col = 1 ; col <=COL_CUMULATIVE; col++){
			row.createCell(col).setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_BOX_STYLE);
		}
	}
	private void writeLine5() {
		//row 5 with all ACT. (GBP) and all that bollocks
		Row row = sheet.createRow(4);
		Cell current = row.createCell(1);
		current.setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_BOX_STYLE);
		for (int col = COL_HEADINGS; col <=COL_CUMULATIVE; col ++){
			current = row.createCell(col);
			current.setCellValue("Plan " + currency);
			current.setCellStyle(ExcelFormatting.FONT_REPORT_HEADING_BOX_STYLE);
		}
	}
	
	private String getShortMonthbyPeriod(int period){
		if (period == 0 ) {
			return "L.Yr";
		} else if (period >= 1 && period <= 12){
			String[] shortDates = new DateFormatSymbols().getShortMonths();
			return shortDates[period-1];
		} else if (period  == 13){
			return "Cumulative";
		} else return "";
	}
	
	public void writeReportHeadings(){
		writeLine1();
		writeLine2();
		
		writeLine4();
		writeLine5();
		
		//hide row 6
		sheet.createRow(5).setHeightInPoints((short) 0);
		
		writeLine7();
	}
	
	public void writeReport(ArrayList<ReportRow> reportRows, Database db){
		System.out.println("Writing "+ sheet.getSheetName());
		writeReportHeadings();
		
		
		EntityDataSet fullDataSet =  (EntityDataSet) db.entityDataSet[getDataSet()][yearMod + 1];
		groupingMap = new HashMap<String, Integer>(); 
        int rowcount = ROW_REPORT_START;
        for (String entityID: db.ownerList){
        	EntityData dataset = fullDataSet.getEntityData(entityID);
	        printGreyRow(sheet.createRow(rowcount++), entityID);
	        for (ReportRow reportRow  :reportRows){
	        	Row row = sheet.createRow(rowcount++);
	        	reportRow.printRow(row, groupingMap, dataset, balanceSheet);     
	        }
	        rowcount++;
        }
	}
	
	public int getDataSet(){
		if (this.budget){return 1;}
		if (this.forecast){return 2;}
		return 0;
	}
	
	public void printGreyRow(Row row, String companyName){
		Cell compNameCell = row.createCell(COL_HEADINGS-1);
		compNameCell.setCellValue(companyName);
		compNameCell.setCellStyle(ExcelFormatting.FONT_GREY_ROW);
		for (int col = COL_HEADINGS ; col <= COL_CUMULATIVE; col++){
			row.createCell(col).setCellStyle(ExcelFormatting.FONT_GREY_ROW);
		}
	}
	
}
