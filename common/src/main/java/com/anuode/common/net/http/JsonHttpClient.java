package com.anuode.common.net.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anuode.common.net.volley.SelfSignSslOkHttpStack;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JsonHttpClient {
	public JsonHttpClient() {
		mBaseUrl = null;
	}

	public JsonHttpClient(String baseUrl) {
		mBaseUrl = baseUrl;
	}
	
	private final String mBaseUrl;
	private final ObjectMapper mMapper = new ObjectMapper();

	private static RequestQueue mRequestQueue = null;
	
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context, new SelfSignSslOkHttpStack());
	}

	public void get(String uri, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.GET, uri, null, new HashMap<>(), onCompletedCallback);
	}

	public void put(String uri, final Object body, final Map<String, String> headers, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.PUT, uri, body, headers, onCompletedCallback);
	}

	public void patch(String uri, final Object body, final Map<String, String> headers, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.PATCH, uri, body, headers, onCompletedCallback);
	}

	public void post(String uri, final Object body, final Map<String, String> headers, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.POST, uri, body, headers, onCompletedCallback);
	}

	public void delete(String uri, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.POST, uri, null, null, onCompletedCallback);
	}

	public void head(String uri, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.HEAD, uri, null, null, onCompletedCallback);
	}

	public void options(String uri, final Object body, final OnCompletedCallback onCompletedCallback) {
		exec(Request.Method.OPTIONS, uri, body, null, onCompletedCallback);
	}

	public void exec(int method, String uri, final Object body, final Map<String, String> headers, final OnCompletedCallback onCompletedCallback) {
		if (mBaseUrl != null) {
			uri = mBaseUrl + uri;
		}
		StringRequest stringObjectRequest = new StringRequest(method, uri, response -> {
                    try {
						if (body instanceof String) {
							onCompletedCallback.onCompleted(null, null, response);
						} else {
							JsonNode objectData = mMapper.readTree(new ByteArrayInputStream(response.getBytes("UTF-8")));
							onCompletedCallback.onCompleted(null, objectData, response);
						}

                    } catch (Exception e) {
                        onCompletedCallback.onCompleted(e, null, null);
                        e.printStackTrace();
                    }
                },
				error -> onCompletedCallback.onCompleted(error, null, null)
		) {
			@Override
			public String getBodyContentType() {
				return "application/json; charset=utf-8";
			}

			@Override
			public byte[] getBody() throws AuthFailureError {
				try {
					if (body != null) {
						if (body instanceof String) {
							try {
								return body.toString().getBytes("utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								return null;
							}
						} else {
							return mMapper.writeValueAsString(body).getBytes();
						}
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected Response<String> parseNetworkResponse(NetworkResponse response) {
				String parsed;
				try {
					parsed = new String(response.data, "utf-8");
				} catch (UnsupportedEncodingException e) {
					parsed = new String(response.data);
				}
				return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				return headers;
			}
		};
		mRequestQueue.add(stringObjectRequest);
	}

	public interface OnCompletedCallback {
		void onCompleted(Exception err, JsonNode objectData, String str);
	}
}
