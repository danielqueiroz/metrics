package com.metrics.stats.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrics.stats.SpringIntegrationTests;
import com.metrics.stats.infra.rest.dto.ParametersDTO;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class CommonSteps extends SpringIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int serverPort;

    protected ResponseEntity response;
    protected File file;

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

    public void uploadFile(final String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Content-type", "text/csv");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public void getRequest(final String url, Class clazz) {
        final String baseUrl = "http://localhost:"+serverPort+"/metrics" + url;
        this.response = restTemplate.getForEntity(baseUrl, clazz);
    }
}
