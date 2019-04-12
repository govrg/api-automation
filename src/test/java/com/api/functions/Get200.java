//https://java2blog.com/android-restful-web-services-tutorial/

package com.api.functions;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class Get200 extends BaseClass {

    //public static final String URLPATH = "https://cdn.rawgit.com/arpitmandliya/AndroidRestJSONExample/master/countries.json";

    @Test
    public void baseUrlReturns200() throws Exception {
        response = client.execute(new HttpGet(URLPATH));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "The status code is not 200");
    }

    @Test
    public void rateLimitReturns200() throws Exception {
        response = client.execute(new HttpGet(URLPATH + "/rate_limit"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "The status code is not 200");
    }

    @Test
    public void searchReposReturns200() throws Exception {
        response = client.execute(new HttpGet(URLPATH + "/search/repositories?q=java"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "The status code is not 200");
    }

    //@Test
    public void check_http_CharSet() throws Exception {

        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");
    }

    //@Test
    public void check_http_MimeType() throws Exception {

        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/json");
    }


}