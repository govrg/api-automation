package com.api.functions;

import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.api.functions.BaseClass.URLPATH;
import static com.api.functions.ResponseUtils.convertJsonToObject;
import static com.api.functions.ResponseUtils.convertJsonToObjectGeneric;
import static org.testng.Assert.assertEquals;

public class BodyTestWithJackson extends BaseClass{

    @Test
    public void returnCorrectLogin() throws IOException {
        response = client.execute(new HttpGet(URLPATH + "/users/govrg"));
        User keyValue = convertJsonToObject(response, User.class);
        assertEquals(keyValue.getLogin(), "govrg");
    }

    @Test
    public void returnCorrectId() throws IOException {
        response = client.execute(new HttpGet(URLPATH + "/users/govrg"));
        User keyValue = convertJsonToObject(response, User.class);
        assertEquals(keyValue.getId(), 46260350);
    }

    @Test
    public void notFounMessageIsCorrect() throws IOException {
        response = client.execute(new HttpGet(URLPATH + "/none"));
        NotFound keyValue = convertJsonToObjectGeneric(response, NotFound.class);
        assertEquals(keyValue.getMessage(), "Not Found");
    }

    @Test
    public void correctRatelimitsAreSet() throws IOException {
        response = client.execute(new HttpGet(URLPATH + "/rate_limit"));
        RateLimit keyValue = convertJsonToObjectGeneric(response, RateLimit.class);
        assertEquals(keyValue.getCoreLimit(), 60);
        assertEquals(keyValue.getSaerchLimit(), "10");
    }
}
