package com.metrics.stats.steps;

import com.metrics.stats.SpringIntegrationTests;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

public class CommonSteps extends SpringIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int serverPort;

    protected ResponseEntity response;

    public void postRequest(final String url, Object param) {
        final String baseUrl = "http://localhost:"+serverPort+"/metrics" + url;

        try {
            URI uri = new URI(baseUrl);
            HttpEntity request = new HttpEntity(param);
            response = restTemplate.postForEntity(uri, request, String.class);
        } catch (URISyntaxException e) {
            Assert.fail("Fail trying to create URI");
        }
    }

    public void getRequest(final String url, Class clazz) {
        final String baseUrl = "http://localhost:"+serverPort+"/metrics" + url;
        this.response = restTemplate.getForEntity(baseUrl, clazz);
    }
}
