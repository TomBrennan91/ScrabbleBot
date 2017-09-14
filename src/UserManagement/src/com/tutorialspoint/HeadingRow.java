package com.tutorialspoint;


import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingThreshold;
//import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Row;

public class HeadingRow extends ReportRow implements Constants{

	private boolean subHeading;
	
	protected HeadingRow(String headingName, boolean subHeading) {
		super(headingName);
		this.subHeading = subHeading;
	}


	public void printRow(Row row, HashMap<String, Integer> groupingMap,  EntityData dataset, boolean BalanceSheet){
		CellStyle format;
		if (subHeading){
			format = ExcelFormatting.FONT_SUB_HEADING;
		} else {
			format = ExcelFormatting.FONT_HEADING;
		}
		Cell headingCell = row.createCell(1);		
		headingCell.setCellValue(getHeadingName());
		headingCell.setCellStyle(format);
		
		for (int col = 2 ; col <= COL_CUMULATIVE ; col++){
			row.createCell(col).setCellStyle(format);
		}
		
	}
}
