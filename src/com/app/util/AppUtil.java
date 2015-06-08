package com.app.util;

import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.base.BaseMessage;

public class AppUtil {
	
	
	
	
	
	//转换
	static public String ucfirst (String str) {
        if (str != null && str != "") {
            str  = str.substring(0,1).toUpperCase() + str.substring(1);
        }
        return str;
    }
	
	
	public static String getSessionId() {
		return null;
	}


	public static String gzipToString(HttpEntity entity) {
		return null;
	}


	public static BaseMessage getMessage(String result) {
		BaseMessage msg = new BaseMessage();
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(result);
			if(jsonObject != null){
				msg.setCode(jsonObject.getString("code"));
				msg.setMessage(jsonObject.getString("message"));
				msg.setResult(jsonObject.getString("result"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public static boolean isEmptyInt(int taskID) {
		return false;
	}

}
