package com.tutorialspoint;

import java.awt.print.Printable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javassist.bytecode.Descriptor.Iterator;
import oracle.jdbc.driver.*;

@SuppressWarnings("unused")
public class Database implements Constants {
	
	public  Object[][] entityDataSet;
	//public static EntityDataSet forecastData;
	public  ArrayList<String> ownerList;
	public  ArrayList<String> companyNameList;
	public  ArrayList<ReportRow> ProfitLossReporting; 
	public  ArrayList<ReportRow> BalanceSheetReporting; 
	public LinkedHashMap<Integer, ReportAttributes> raMap;
	
	public Database(int year) {
	
		getEntityList();
		
		getAllRawData(year);
		
		this.ProfitLossReporting =  getReporting(REPORT_PROFIT_LOSS);
		this.BalanceSheetReporting = getReporting(REPORT_BALANCE_SHEET);
	
		GetReportAttributes();
		

	}

	private void getAllRawData(int year) {
		long startTime = System.nanoTime();
		ArrayList<String> dataSources = getDataSources();
		this.entityDataSet =  new Object[3][3];
		//this big might be faster if did fewer, larger queries...
		for (int yearMod = 0 ; yearMod <=2 ; yearMod++){ //LOOP OVER YEARMOD
			for (int reportType = 0 ; reportType < dataSources.size() ; reportType++){ //LOOP OVER DATATYPES
				EntityDataSet currentEntityDataSet = new EntityDataSet(dataSources.get(reportType), yearMod);
				for (String entityID : ownerList){ // LOOP OVER ENTITIES
					currentEntityDataSet.Data.put(entityID,  getRawData( year - 1 + yearMod, dataSources.get(reportType), entityID));	
				}
				this.entityDataSet[yearMod][reportType] =  currentEntityDataSet;	
			}
		}
		
		System.err.println("time to get all raw data " +((System.nanoTime() - startTime) / 1000000000) + " seconds");
	}
	
	public ArrayList<String> getDataSources(){
		ArrayList<String> dataSources = new ArrayList<String>();
		dataSources.add("VIEW_BUDGET_REPORT_DATA");
		dataSources.add("VIEW_ACTUAL_REPORT_DATA");
		dataSources.add("VIEW_FORECAST_REPORT_DATA");
		return dataSources;
	}
	
	public static ResultSet queryDB(String query) throws SQLException, ClassNotFoundException{
			System.out.println("Running query: '" + query );
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection m_Connection = DriverManager.getConnection(
	        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=10.0.1.157)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=SYNORA4)))", "DEVPENT", "DEVPENT");
	        ResultSet rs =  m_Connection.createStatement().executeQuery(query);
	        rs.setFetchSize(1000);
			return rs;
	}
	
	
	private void GetReportAttributes(){
		raMap = new LinkedHashMap<Integer, ReportAttributes>();
		int reportCount = 0;
		try{
			ResultSet rs = queryDB("SELECT * FROM REPORTATTRIBUTES where " + COL_ENABLED + " = 1 AND " //(" + COL_BUDGET + " = 1  OR " + COL_FORECAST + " = 1 ) AND "
																	  + COL_NORMAL_REPORT + " = 1 AND " + COL_REPORT_TYPE  + " != 'Cash Flow' AND "
																	  + COL_REPORT_TYPE + " != 'KPI' order by REPORT_ID");
			while(rs.next()){
				int id = rs.getInt("REPORT_ID");
				ReportAttributes currentLine = new ReportAttributes(this,
																	rs.getString(COL_REPORT_NAME), 
																	id,
																	rs.getString(COL_REPORT_TYPE),
																	rs.getInt(COL_YEAR_MOD),
																	(rs.getInt(COL_BUDGET) == 1), 
																	(rs.getInt(COL_FORECAST) == 1),
																	rs.getString(COL_DATA_FILTER));
				System.out.println(currentLine.toString());
				raMap.put(id, currentLine);
				reportCount++;
			}	
		System.out.println(reportCount + " Reports added to queue");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
	}
	
	private void getEntityList(){
		LinkedHashMap<String, String> entityMap = new LinkedHashMap<String, String>();
		ownerList = new ArrayList<String>();
		companyNameList = new ArrayList<String>();
		try{
			ResultSet rs = queryDB("SELECT * FROM COMPANY where ENTITY_TYPE = 'SUBSIDIARY' order by OWNER");
			
			int maxEntities = 1;
			int currententity = 1;
			
			while (rs.next()){
				ownerList.add(rs.getString(COL_OWNER));
				companyNameList.add(rs.getString(COL_COMPANY_NAME));
				//System.out.println(rs.getString(COL_OWNER));
				if (currententity++ > maxEntities){break;};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private EntityData getRawData(int year, String dataSource, String entityID){
		EntityData data = new EntityData(entityID); 

		try{
			ResultSet rs = queryDB("SELECT * FROM " + dataSource + " where OWNER = '"+  entityID
							+ "' AND ACCOUNT_YEAR = '" + year + "'  order by OWNER,  LOCAL_TAG");
			ResultSetMetaData md = rs.getMetaData();
				
			while (rs.next()){
				int[] values = new int[12];
				for (int period = 1; period <=12 ; period++){
					values[period-1] = rs.getInt("PERIOD " + period);
				}
				data.entityData.put( rs.getString("LOCAL_TAG"), values);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private String formatEntityListForSql(ArrayList<String> entityList){
		StringBuilder sb = new StringBuilder();
		for (String entityID : entityList){
			sb.append('\'').append(entityID).append('\'').append(',');
		}
		sb.delete(sb.length() - 1 , sb.length());
		return sb.toString();
	}
	
    private ArrayList<ReportRow>  getReporting (String reportType) {	
    	ArrayList<ReportRow> reporting = new ArrayList<ReportRow>();
    	
    	int count = 0;
    	String ReportType;
		try{
			ResultSet rs = queryDB("SELECT * FROM Reporting where REPORT_TYPE = '" + reportType + "' order by ROW_ORDER");
			
			System.out.println("Parsing " + reportType + " Business Logic...");
			
			String headingName;
			String headingType;
	        loopResultset: while (rs.next()){

        		//use factory method
	        	headingName = rs.getString(COL_HEADING_NAME);
	        	headingType = rs.getString("Heading_Type");
        		switch (headingType){
        			case "Grouping":
        				reporting.add(new GroupingRow(headingName, rs.getString(COL_BUSINESS_RULE), false));
        				break;
        			case "Heading":
        				reporting.add(new HeadingRow(headingName, false));
        				break;
        			case "Sub-heading":
        				reporting.add(new HeadingRow(headingName, true));
        				break;
        			case "Sub-total":	
        				reporting.add(new TotalRow(headingName, rs.getString(COL_BUSINESS_RULE), false, true));
        				break;
        			case "Total":
        				reporting.add(new TotalRow(headingName, rs.getString(COL_BUSINESS_RULE), false , false));
        				break;
        			case "Retained Profit":
        				reporting.add(new RetainedProfitRow(headingName, rs.getString(COL_BUSINESS_RULE), false));
        				break;
        			//case "Formula":	
        			default:
        				count++;
        				System.err.println(rs.getString(headingType));
        				break;
        		}
	        	
	        }
	        if (count > 0 ){
		        System.err.println(count + " rows could not be added to the Report " + reportType);
	        }
	        System.out.println(reporting.size() + " rows added to " + reportType + " List");
	        return reporting;
	       
		} catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
    }
}
