package com.tutorialspoint;

public abstract class DataRow extends ReportRow{
	
	private boolean negative;
	private String businessRule;
	
	protected DataRow(String headingName, String businessRule, boolean negative) {
		super(headingName);
		this.businessRule = businessRule;
		this.negative = negative;
	}
	
	public String getBusinessRule(){
		return businessRule;
	}
	
	public boolean negative(){
		return negative;
	}

}
