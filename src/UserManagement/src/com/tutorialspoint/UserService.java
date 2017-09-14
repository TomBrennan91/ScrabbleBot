package com.tutorialspoint;  

import java.io.ByteArrayOutputStream;
import java.io.IOException;

//import java.util.List; 
//import javax.ws.rs.GET; 
//import javax.ws.rs.Path; 
//import javax.ws.rs.Produces; 
//import javax.ws.rs.core.MediaType;  
//@Path("/UserService") 
//
//public class UserService {  
//   UserDao userDao = new UserDao();  
//   @GET 
//   @Path("/users") 
//   @Produces(MediaType.APPLICATION_OCTET_STREAM) 
//   public List<User> getUsers(){ 
//      return userDao.getAllUsers(); 
//   }  
//}

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;  
@Path("/UserService") 
public class UserService {  
   //UserDao userDao = new UserDao();  
  
   @GET 
   @Path("/CloudCFOSSReports.xls") 
   @Produces(MediaType.APPLICATION_OCTET_STREAM) 
   public byte[] getUsers() { 
	   //Database.budgetData =  Database.getRawData("pderb", 2016, "BUDGET");
	   
	   
	   SXSSFWorkbook wb =  ReportBook.createExcelFile(2016, 6);//, new String[]{"pderb", "plinc"});	
	   ByteArrayOutputStream bos = new ByteArrayOutputStream();
	   
	   try {
	       wb.write(bos);
	   } catch (IOException e){
		   e.printStackTrace();
	   } finally {
	        try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	   byte[] bytes = bos.toByteArray();
	   return bytes;
	   
   }  
}