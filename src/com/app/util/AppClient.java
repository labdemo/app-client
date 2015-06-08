package com.app.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.app.base.C;

public class AppClient {

	private static final int CS_NONE = 0;
	private static final int CS_GZIP = 1;

	private String apiURL;
	private HttpParams httpParams;
	private HttpClient httpClient;
	private int timeOutConnection = 10000;
	private int timeOutSocket = 10001;
	private int compress = CS_NONE;

	private String charSet = HTTP.UTF_8;

	public AppClient(String url) {
		initClient(url);
	}

	private void initClient(String url) {
		this.apiURL = C.api.base + url;
		String apiSid = AppUtil.getSessionId();
		if (apiSid != null && apiSid.length() > 0) {
			this.apiURL += "?sid=" + apiSid;
		}
		httpParams = new BasicHttpParams();
		HttpConnectionParams
				.setConnectionTimeout(httpParams, timeOutConnection);
		HttpConnectionParams.setSoTimeout(httpParams, timeOutSocket);

		httpClient = new DefaultHttpClient(httpParams);
	}

	public String get() {
		try {
			HttpGet httpGet = headerFilter(new HttpGet(this.apiURL));
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String httpResult = resultFilter(httpResponse.getEntity());
				return httpResult;
			} else {
				return null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String post(HashMap<String, String> taskMap) {
		HttpPost httpPost = headerFilter(new HttpPost(apiURL));
		ArrayList<NameValuePair> postParams = new ArrayList<NameValuePair>();
		Iterator it = taskMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			postParams.add(new BasicNameValuePair(entry.getKey().toString(),
					entry.getValue().toString()));
		}
		try {
			if (charSet != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(postParams, charSet));
			} else {
				httpPost.setEntity(new UrlEncodedFormEntity(postParams, charSet));
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String httpResult = resultFilter(httpResponse.getEntity());
				return httpResult;
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private HttpGet headerFilter(HttpGet httpGet) {
		switch (compress) {
		case CS_GZIP:
			httpGet.addHeader("Accept-Encoding", "gzip");
			break;
		default:
			break;
		}
		return httpGet;
	}

	private HttpPost headerFilter(HttpPost httpPost) {
		switch (compress) {
		case CS_GZIP:
			httpPost.addHeader("Accept-Encoding", "gzip");
			break;
		default:
			break;
		}
		return httpPost;
	}

	private String resultFilter(HttpEntity entity) {
		String result = null;
		switch (compress) {
		case CS_GZIP:
			result = AppUtil.gzipToString(entity);
			break;
		default:
			try {
				result = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		return result;
	}

}
