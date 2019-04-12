//https://java2blog.com/android-restful-web-services-tutorial/

package com.api.functions;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class Get404 extends BaseClass {

    @Test
    public void check_http_respons_404() throws Exception {
        response = client.execute(new HttpGet(URLPATH+ "/none"));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 404, "The status code is not 404");
    }
}