package com.tutorialspoint;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RetainedProfitRow extends DataRow  implements Constants{

	protected RetainedProfitRow(String headingName, String businessRule, boolean negative) {
		super(headingName, businessRule, negative);
		// TODO Auto-generated constructor stub
	}
	public void printRow(Row row, HashMap<String, Integer> groupingMap, EntityData dataset, boolean BalanceSheet){
		CellStyle format = ExcelFormatting.FONT_TOTAL;
    	Cell headingCell = row.createCell(1);
    	headingCell.setCellValue(getHeadingName());
    	headingCell.setCellStyle(format);
    	groupingMap.put(getHeadingName().toLowerCase(), row.getRowNum());
    	
    	for (int col = 2 ; col <= COL_CUMULATIVE ; col++){
    		Cell cell = row.createCell(col);
    		cell.setCellStyle(format);
    		cell.setCellValue(0.0);
    	}
    	
		
	}
}
