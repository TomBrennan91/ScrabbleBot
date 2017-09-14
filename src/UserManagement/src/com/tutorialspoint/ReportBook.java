package com.tutorialspoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportBook implements Constants {

	public static XSSFWorkbook outWB;
	
    public static void  writetoexcel(SXSSFWorkbook sxssfWorkbook){
	
		try {
            FileOutputStream out = new FileOutputStream(new File(OUT_PATH + OUT_FILE_NAME));
            sxssfWorkbook.write(out);
            out.close();
            System.out.println(OUT_FILE_NAME + " written successfully on disk.");
        } catch (IOException e){
        	System.err.println("the  file is open in excel, so it couldnt be saved...");
        }
        
    }
    
    public static SXSSFWorkbook createExcelFile(int year, int period){
    	Database database = new Database(year);
		SXSSFWorkbook myExcel = new SXSSFWorkbook(100);
		ExcelFormatting.initialiseStyles(myExcel);
		for (int reportID : database.raMap.keySet()){
			ReportAttributes ra = database.raMap.get(reportID);
			new ReportSheet(ra.sheetName, myExcel, period, year, ra.budget, ra.forecast, "GBP", "Pentagon", ra.rowList, database,  ra.yearMod, ra.reportType);
			//currentReport.writeReport(ra.rowList, database);
		}
		
		return myExcel;
    }
    
}
	
