package com.tutorialspoint;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class GroupingRow extends DataRow implements Constants{

	public GroupingRow(String headingName, String businessRule, boolean negative) {
		super(headingName, businessRule, negative);
	}
	
	public void printRow(Row row, HashMap<String, Integer> groupingMap, EntityData dataset, boolean BalanceSheet){
		CellStyle format = ExcelFormatting.FONT_DEFAULT;
    	Cell headingCell = row.createCell(1);
    	headingCell.setCellValue(getHeadingName());
    	headingCell.setCellStyle(format);
    	groupingMap.put(getHeadingName().toLowerCase(), row.getRowNum());
    	int[]values = dataset.entityData.get(getBusinessRule());
    	
    	//no row find, put in 0s
    	if (values == null) {
    		values = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
    	}
    	int cumulative = 0;
	    for (int col = 0; col <  values.length; col ++){
    		Cell cell = row.createCell(col+ 3);
    		cumulative += values[col];
    		if (BalanceSheet){
    			cell.setCellValue(cumulative);
	    	}else{
    			cell.setCellValue(values[col]);
    		}
    		cell.setCellStyle(format);
	    }
	    
	    //fill in cumulative column
		Cell cell = row.createCell(COL_CUMULATIVE);
		cell.setCellValue(cumulative);
		cell.setCellStyle(format);
	}

	
}

