//https://java2blog.com/android-restful-web-services-tutorial/

package com.api.functions;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.api.functions.ResponseUtils.getHeader;
import static org.apache.http.entity.ContentType.getOrDefault;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */





public class CheckHeader extends BaseClass {

    @Test
    public void checkCharSet() throws Exception {
        response = client.execute(new HttpGet(URLPATH));
        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");
    }

    @Test
    public void checkMimeType() throws Exception {
        response = client.execute(new HttpGet(URLPATH));
        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/json");
    }

    @Test
    public void checkServerIsGithub() throws Exception {
        response = client.execute(new HttpGet(URLPATH));
        String headerValue = getHeader(response, "Server");
        Assert.assertEquals(headerValue, "GitHub.com");
    }

    @Test
    public void checkXrateLimitIsSixty() throws Exception {
        response = client.execute(new HttpGet(URLPATH));
        String limitValue = getHeader(response, "X-RateLimit-Limit");
        Assert.assertEquals(limitValue, "60");
    }

    private String getHeader1(CloseableHttpResponse response, String headerName) {
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";
        for (Header header : httpHeaders) {
            if(headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }
        if(returnHeader.isEmpty()) {
            throw new RuntimeException("Didnt find the header " + headerName);
        }
        return returnHeader;
    }
}