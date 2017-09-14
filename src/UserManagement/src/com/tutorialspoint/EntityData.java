package com.tutorialspoint;

import java.util.HashMap;

public class EntityData{
	public EntityData(String entityID) {
		this.entityID = entityID;
		this.entityData = new HashMap<String, int[]>();
	}

	String entityID;
	HashMap<String, int[]> entityData;
	
	public int getValue(String localTag, int period){
		int[] values = entityData.get(localTag);
		return values[period];
	}
	
	
}
