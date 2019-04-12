package com.api.functions;

import entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGING;
import static org.apache.http.entity.ContentType.getOrDefault;
import static org.testng.Assert.assertEquals;

public class BodyTestMap extends BaseClass{

    @Test
    public void returnCorrectlogin() throws Exception {
        response = client.execute(new HttpGet(URLPATH + "/users/govrg"));
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject  jsonObject = new JSONObject(jsonBody);
        String keyValue =  (String) getValueFor(jsonObject, LOGING);
        assertEquals(keyValue, "govrg");
    }

    @Test
    public void returnCorrectId() throws Exception {
        response = client.execute(new HttpGet(URLPATH + "/users/govrg"));
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject  jsonObject = new JSONObject(jsonBody);
        Integer keyValue =  (Integer) getValueFor(jsonObject, ID);
        assertEquals(keyValue, Integer.valueOf(46260350));
    }
    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
