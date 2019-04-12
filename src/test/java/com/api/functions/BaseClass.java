package com.api.functions;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    protected static final String URLPATH = "https://api.github.com";

    @BeforeSuite
    public void baseSuiteSetup() {
        System.out.print("Base setup for all tests");
    }

    @BeforeMethod
    public void localMethodSetup() throws IOException {
        System.out.print("\nDoing the baseMethodSetup ");
        client = HttpClientBuilder.create().build();
        //response = client.execute(new HttpGet(URLPATH));
    }

    @AfterMethod
    public void localMethodCleanup() throws IOException {
        System.out.print("\nDoing the baseMethodCleanup ");
        client.close();
        response.close();
    }
}
