package com.api.functions;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) {
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";
        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }
        if (returnHeader.isEmpty()) {
            throw new RuntimeException("Didnt find the header " + headerName);
        }
        return returnHeader;
    }

    public static User convertJsonToObject(CloseableHttpResponse response, Class<User> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) //this to tell Jackson to not handle properties that not are defined in our User class
                .readValue(jsonBody, clazz);
    }

    public static <T> T convertJsonToObjectGeneric(CloseableHttpResponse response, Class<T> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) //this to tell Jackson to not handle properties that not are defined in our User class
                .readValue(jsonBody, clazz);
    }

    public static String convertObjectToJsonGeneric(Object object) throws IOException {
        return new ObjectMapper()
                .writeValueAsString(object);

    }
}
