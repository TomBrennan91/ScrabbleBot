package com.tutorialspoint;

import java.util.ArrayList;

public class ReportAttributes implements Constants{

	String sheetName;
	int id;
	String reportType;
	//int[] dependencies;
	int yearMod;
	boolean budget;
	boolean forecast;
	String dataFilter;
	ArrayList<ReportRow> rowList;
	
	public ReportAttributes(Database database, String sheetName, int id, String reportType, int yearMod,
			boolean budget, boolean forecast, String dataFilter) {
		super();
		this.sheetName = sheetName;
		this.id = id;
		this.reportType = reportType;
		this.yearMod = yearMod;
		this.budget = budget;
		this.forecast = forecast;
		this.dataFilter = dataFilter;
		switch (reportType){
		case REPORT_PROFIT_LOSS:
			this.rowList = database.ProfitLossReporting;
			//System.out.println(REPORT_PROFIT_LOSS);
			break;
		case REPORT_BALANCE_SHEET: 
			this.rowList = database.BalanceSheetReporting;
			//System.out.println(REPORT_BALANCE_SHEET);
			break;
		default:	
			System.err.println("Report type: " + reportType + " not recognised");
			break;
		}
	}


	@Override
	public String toString() {
		return "ReportAttributes [sheetName=" + sheetName + ", id=" + id + ", reportType=" + reportType
				+ ", dependencies=" +  ", yearMod=" + yearMod + ", budget=" + budget
				+ ", forecast=" + forecast + ", dataFilter=" + dataFilter + "]";
	}
	
	
	
}
