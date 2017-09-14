package com.tutorialspoint;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

@SuppressWarnings("deprecation")
public class ExcelFormatting implements Constants {
	
	public static DataFormat dataFormat;
	public static CellStyle FONT_HEADING;
	public static CellStyle FONT_SUB_HEADING;
	public static CellStyle FONT_TOTAL;
	public static CellStyle FONT_SUB_TOTAL;
	public static CellStyle FONT_DEFAULT;
	public static CellStyle FONT_GREY_ROW;
	public static CellStyle FONT_REPORT_HEADING_BOX_STYLE;
	public static CellStyle FONT_REPORT_HEADING_STYLE;
	
	public static void initialiseStyles(SXSSFWorkbook myExcel){
		System.out.println("initialising Workbook Format styles");
		dataFormat = myExcel.createDataFormat();
		FONT_HEADING = initialiseHeading(myExcel);
		FONT_SUB_HEADING = initialiseSubHeading(myExcel);
		FONT_DEFAULT = initialiseDefaultStyle(myExcel);
		FONT_SUB_TOTAL = initialiseSubTotalStyle(myExcel);
		FONT_TOTAL = initialiseTotalStyle(myExcel);
		FONT_GREY_ROW = initialiseGreyRowStyle(myExcel);
		FONT_REPORT_HEADING_BOX_STYLE = initialiseReportHeadingBoxStyle(myExcel);
		FONT_REPORT_HEADING_STYLE = initialiseReportHeadingStyle(myExcel);
	}
	
	static CellStyle initialiseHeading(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)13);
		font.setBold(true);
		font.setFontName("Calibri");
		style.setFont(font);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		return style;
	}
	static CellStyle initialiseSubHeading(SXSSFWorkbook myExcel){
		CellStyle style = (CellStyle) myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBold(true);
		font.setFontName("Calibri");
		style.setFont(font);
		
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		return style;
	}
	static CellStyle initialiseSubTotalStyle(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)11);
		font.setItalic(true);
		font.setFontName("Calibri");
		style.setFont(font);
		style.setDataFormat(dataFormat.getFormat("#,##0;(#,##0)"));
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		return style;
	}
	static CellStyle initialiseTotalStyle(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)11);
		font.setBold(true);
		font.setFontName("Calibri");
		style.setFont(font);
		style.setDataFormat(dataFormat.getFormat("#,##0;(#,##0)"));
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		return style;
	}
	static CellStyle initialiseDefaultStyle(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName("Calibri");
		style.setDataFormat(dataFormat.getFormat("#,##0;(#,##0)"));
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setFont(font);
		return style;
	}
	static CellStyle initialiseGreyRowStyle(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)14);
		font.setFontName("Calibri");
		font.setBold(true);
		
//		XSSFColor color = new XSSFColor(new Color(100, 100, 100));
//		style.setFillForegroundColor(color);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setFont(font);
		return style;
	}
	static CellStyle initialiseReportHeadingBoxStyle(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)11);
		font.setBold(true);
		font.setFontName("Calibri");
		style.setFont(font);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	static CellStyle initialiseReportHeadingStyle(SXSSFWorkbook myExcel){
		CellStyle style = myExcel.createCellStyle();
		Font font = myExcel.createFont();
		font.setFontHeightInPoints((short)11);
		font.setBold(true);
		font.setFontName("Calibri");
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	
	public static SXSSFSheet initialiseReportSheet(SXSSFWorkbook wb, String sheetName){
		SXSSFSheet sheet = wb.createSheet(sheetName);
		//ExcelFormatting.initialiseStyles(wb);
        //for (int col = 1; col < 16; col++){sheet.setDefaultColumnStyle(col, ExcelFormatting.FONT_DEFAULT);}
        sheet.setColumnWidth(0, 256*2);
        sheet.setColumnWidth(1, 256*44);
        sheet.setColumnWidth(2, 0);
        for (int col = 3 ; col <= 15 ; col ++){
        	sheet.setColumnWidth(col, 256*15);
        }
        return sheet;
	}
}
