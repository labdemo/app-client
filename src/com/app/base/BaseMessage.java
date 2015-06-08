package com.app.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.util.AppUtil;

public class BaseMessage {
	
	private String code;
	private String message;
	private String resultSrc;
	private Map<String, BaseModel> resultMap;
	private Map<String, ArrayList<? extends BaseModel>> resultList;
	
	public BaseMessage(){
		this.resultMap = new HashMap<String, BaseModel>();
		this.resultList = new HashMap<String, ArrayList<? extends BaseModel>>();
	}
	
	@Override
	public String toString() {
		return code + "|" + message + "|" + resultSrc;
	}
	
	
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getResult(){
		return this.resultSrc;
	}
	
	public Object getResult(String modelName) throws Exception {
		Object model = this.resultMap.get(modelName);
		if(model == null){
			throw new Exception("Message data is empty");
		}
		return model;
	}
	
	public ArrayList<? extends BaseModel> getResultList(String modelName) throws Exception{
		ArrayList<? extends BaseModel> modelList = this.resultList.get(modelName);
		if(modelList == null || modelList.size() == 0){
			return null;
		}
		return modelList;
	}
	
	public void setResult(String result) throws Exception{
		if(result.equals("null")){
			resultSrc = "";
			return;
		}
		this.resultSrc = result;
		if(result.length() > 0){
			JSONObject jsonObject = new JSONObject(result);
			Iterator<String> it = jsonObject.keys();
			while(it.hasNext()){
				String jsonKey = it.next();
				String modelName = getModelName(jsonKey);
				String modelClassName = "com.app.model" + modelName;
				JSONArray modelJsonArray = jsonObject.optJSONArray(jsonKey);
				if(modelJsonArray == null){
					JSONObject modelJsonObject = jsonObject.optJSONObject(jsonKey);
					if(modelJsonObject == null){
						throw new Exception("Message result is invalid");
					}
					this.resultMap.put(modelName, json2model(modelClassName, modelJsonObject));
				} else {
					ArrayList<BaseModel> modelList = new ArrayList<BaseModel>();
					for(int i = 0; i < modelJsonArray.length(); i++){
						JSONObject modelJsonObject = modelJsonArray.optJSONObject(i);
						modelList.add(json2model(modelClassName, modelJsonObject));
					}
					this.resultList.put(modelName, modelList);
				}
			}
		}
	}

	
	private BaseModel json2model(String modelClassName, JSONObject modelJsonObject) throws Exception {
		BaseModel modelObject = (BaseModel) Class.forName(modelClassName).newInstance();
		Class<? extends BaseModel> modelClass = modelObject.getClass();
		Iterator<String> it = modelJsonObject.keys();
		while(it.hasNext()){
			String varField = it.next();
			String varVlaue = modelJsonObject.getString(varField);
			Field field = modelClass.getDeclaredField(varField);
			field.setAccessible(true);
			field.set(modelObject, varVlaue);
		}
		return modelObject;
	}

	private String getModelName(String str) {
		//split --> 将给定的字符串按照给定的分割符分割为多个数组
		String[] strArray = str.split("\\W");
		if(strArray.length > 0){
			str = strArray[0];
		}
		return AppUtil.ucfirst(str);
	}
}