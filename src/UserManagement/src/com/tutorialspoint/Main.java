package com.tutorialspoint;

public class Main 
{
	// we want to pass in entityids, year, period, reportname(s) and have it spit out reports
	public static void main(String[] args) {
		   ReportBook.writetoexcel(ReportBook.createExcelFile(2016, 6 ));//, new String[]{"pderb", "plinc"})  );	
	}
}

