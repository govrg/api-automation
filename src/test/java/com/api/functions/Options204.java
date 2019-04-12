package com.api.functions;

import org.apache.http.client.methods.HttpOptions;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Options204 extends BaseClass{

    @Test
    public void optionsWhichMethodRequests() throws IOException {

        String header = "Access-Control-Allow-Methods";
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";
        response = client.execute(new HttpOptions( URLPATH ));
        String actualValue = ResponseUtils.getHeader(response,header);
        assertEquals(actualValue, expectedReply);

    }
}
