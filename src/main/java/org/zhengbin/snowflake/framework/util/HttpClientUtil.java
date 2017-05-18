package org.zhengbin.snowflake.framework.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * GET、POST 请求工具类
 * Created by zhengbinMac on 2017/5/9.
 */
public class HttpClientUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送"GET"请求
     * @param url   请求地址
     * @return
     */
    public static String doGetStr(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String json = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                json = JsonUtil.toJson(result);
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientUtil, Exception: {}", e);
        }
        return json;
    }

    /**
     * 发送"POST"请求
     * @param url       请求地址
     * @param headers   请求头
     * @return
     */
    public static String doGetStr(String url, Map<String, String> headers) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        String json = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                json = JsonUtil.toJson(result);
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientUtil, Exception: {}", e);
        }
        return json;
    }

    /**
     * POST 请求
     * @param url
     * @return
     */
    public static String doPostStr(String url, String outStr) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String json = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            json = JsonUtil.toJson(result);
        } catch (IOException e) {
            LOGGER.error("HttpClientUtil, IOException: {}", e);
        }
        return json;
    }

    public static String doPostStr(String url, String requestData, Map<String, String> headers) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        String result = null;
        try {
            // setEntity 设置请求参数
            httpPost.setEntity(new StringEntity(requestData, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            LOGGER.error("HttpClientUtil, IOException: {}", e);
        }
        return result;
    }

}
