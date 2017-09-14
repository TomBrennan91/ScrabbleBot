package com.tutorialspoint;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;

abstract class ReportRow {
	
	private String headingName;

	//private String headingType;
	
	public String getHeadingName() {
		return headingName;
	}

	//public String getHeadingType(){
	//	return headingType;
	//}

	protected ReportRow (String headingName) {
		this.headingName = headingName;
		
	}
	
	public void printRow(Row row, HashMap<String, Integer> groupingMap, EntityData dataset, boolean BalanceSheet){
    	row.createCell(1).setCellValue(getHeadingName());
	}
	
}
