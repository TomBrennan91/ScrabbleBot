package com.tutorialspoint;

import java.util.HashMap;

public class EntityDataSet {
	
	String DataType;
	int year;
	HashMap<String, EntityData> Data;

	//ArrayList<RawData> Data;
	
	public EntityDataSet(String DataType, int year) {
		this.DataType= DataType;
		this.year = year;
		this.Data = new HashMap<String, EntityData>();
	}
	
	public EntityData getEntityData (String entityID){
		return Data.get(entityID);
	}
	
	
}
