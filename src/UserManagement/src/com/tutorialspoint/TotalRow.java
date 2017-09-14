package com.tutorialspoint;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class TotalRow extends DataRow implements Constants{
	boolean subTotal;
	
	protected TotalRow(String headingName, String businessRule, boolean negative, boolean subTotal) {
		super(headingName, businessRule, negative);
		this.subTotal = subTotal;
	}
	
	public void printRow(Row row, HashMap<String, Integer> groupingMap, EntityData dataset, boolean BalanceSheet){
		
		CellStyle format;
		if (subTotal){
			format = ExcelFormatting.FONT_SUB_TOTAL;
		} else {
			format = ExcelFormatting.FONT_TOTAL;
		}
		 
		Cell headingCell = row.createCell(1);
		groupingMap.put(getHeadingName().toLowerCase(), row.getRowNum());
		
		headingCell.setCellValue(getHeadingName());
		headingCell.setCellStyle(format);
		String[] parsedBusinessRule = getBusinessRule().split("#");
		StringBuilder formula = new StringBuilder();
		
		boolean divideFlag = false;
	
		for (int i = 0 ; i < parsedBusinessRule.length ; i++){
			switch (parsedBusinessRule[i]){
				case "":
					break;
				case "/":
					divideFlag = true;
				case "+":
				case "-":
				case "(":
				case ")":
				case "*":
					formula.append(parsedBusinessRule[i]);
					break;
				default:
					formula.append("@").append(groupingMap.get(parsedBusinessRule[i].toLowerCase()));
					break;
			}
		}
		
		if (divideFlag){
			formula.insert(0, "IFERROR(");
			formula.append(",0)");
		}
		
		for (int col = 2; col <= COL_CUMULATIVE; col++){
			Cell datacell = row.createCell(col);
			try {
				datacell.setCellFormula(formula.toString().replace('@', getColLetterFromIndex(col)));
			} catch (Exception e) {
				System.err.println(formula.toString().replace('@', getColLetterFromIndex(col)) + " " + getBusinessRule());
				break;
			}
			datacell.setCellStyle(format);
		}
	}
	
	char getColLetterFromIndex(int col){
		return (char) ('a' + col);
	}
	
}
