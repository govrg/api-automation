package com.api.functions;

import entities.Credentials;
import entities.GitHub;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import static org.testng.Assert.assertEquals;

public class DeleteAndPost extends BaseClass{

    @Test(priority = -10)
    public void deleteIsUnauthorized() throws IOException {

        response = client.execute(new HttpDelete( URLPATH + "/repos/govrg/deleteme"));
        int actualValue = response.getStatusLine().getStatusCode();
        assertEquals(actualValue, 403);

    }

    @Test(priority = -5)
    public void deleteIsAuthorizedWithRepo() throws IOException {

        HttpDelete request = new HttpDelete( URLPATH + "/repos/govrg/deleteme");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token " + Credentials.TOKENGITHUB);
        response = client.execute(request);
        int actualValue = response.getStatusLine().getStatusCode();
        assertEquals(actualValue, 204, "Could not delete the repo. Maybe no repo to delete? ");

    }

    @Test
    public void deleteIsAuthorizedButNoRepo() throws IOException {

        HttpDelete request = new HttpDelete( URLPATH + "/repos/govrg/deleteme1");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token " + Credentials.TOKENGITHUB);
        response = client.execute(request);
        int actualValue = response.getStatusLine().getStatusCode();
        assertEquals(actualValue, 404);

    }

    @Test(priority = -15)
    public void postNewRepoDeletme() throws IOException {

        HttpPost request = new HttpPost( URLPATH + "/user/repos");
        String auth = Credentials.EMAIL + ":" + Credentials.PASSWORD;
        byte[] encodeAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodeAuth);

        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        //request.setHeader(HttpHeaders.AUTHORIZATION, "token " + Credentials.TOKENGITHUB);

        GitHub github = new GitHub();
        String jsonbody = ResponseUtils.convertObjectToJsonGeneric(github);

        request.setEntity(new StringEntity(jsonbody, ContentType.APPLICATION_JSON));

        response = client.execute(request);
        int actualValue = response.getStatusLine().getStatusCode();
        assertEquals(actualValue, 201, "The repo could not be created");

    }
}
