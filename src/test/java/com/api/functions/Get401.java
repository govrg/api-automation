package com.api.functions;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Get401 extends BaseClass {

    @DataProvider
    private Object[][] endpoints(){
        return new Object[][] {
                {"/user"},
                {"/user/followers"},
                {"/notifications"}
        };
    }

    @Test(dataProvider = "endpoints")
    public void userReturns401(String endpoint) throws Exception {
        response = client.execute(new HttpGet(URLPATH + endpoint));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 401, "The status code is not 401");
    }

}
