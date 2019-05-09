package com.framework.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.framework.library.GlobalVariables;

public class JSONUtility {
	public static LinkedHashMap<String, String> dataMap=new LinkedHashMap<>();

	public static String getTCData(String filename, String tcId,String field) {
		try {
			JSONObject jsonObj=null;
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(GlobalVariables.TESTDATAPATH + filename + ".json"));
			jsonObj = new JSONObject(obj.toString());
			JSONArray arr = jsonObj.getJSONArray(tcId);
			for (int i = 0; i < arr.length(); i++){
				JSONObject post_id = arr.getJSONObject(i);

				@SuppressWarnings("unchecked")
				Iterator<String> keys = post_id.keys();
				while(keys.hasNext()) {
					String key = keys.next();
					Object value = post_id.get(key);
					if(key.equalsIgnoreCase(field)) {
						return value.toString();
					}
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONArray getTCDataUpdated(JSONObject jsonObj,String tcId,String field) {
		try {
			JSONArray arr = jsonObj.getJSONArray(tcId);
			for (int i = 0; i < arr.length(); i++){

				JSONObject post_id = arr.getJSONObject(i);
				@SuppressWarnings("unchecked")
				Iterator<String> keys = post_id.keys();
				while(keys.hasNext()) {
					String key = keys.next();
					if(key.equalsIgnoreCase(field)) {
						return post_id.getJSONArray(field);
					}
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static LinkedHashMap<String, String> getLinkCheckerDataMap(JSONObject jsonObj) {
		try {
			String page="";
			String url="";
			JSONArray arr = jsonObj.getJSONArray("LINK_CHECKER");
			for (int i = 0; i < arr.length(); i++){
				JSONObject post_id = arr.getJSONObject(i);
				@SuppressWarnings("unchecked")
				Iterator<String> keys = post_id.keys();
				while(keys.hasNext()) {
					String key = keys.next();
					Object value = post_id.get(key);
					if(key.equalsIgnoreCase("Page")) {
						page=value.toString();
					}else if (key.equalsIgnoreCase("URL")) {
						url=value.toString();
					}
					dataMap.put(page, url);
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return dataMap;
	}

	public static JSONObject getTCDataJsonObject(JSONObject jsonObj,String tcId) {
		try {
			JSONArray arr = jsonObj.getJSONArray(tcId);
			return arr.getJSONObject(0);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}