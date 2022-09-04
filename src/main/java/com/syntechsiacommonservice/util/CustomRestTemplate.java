package com.syntechsiacommonservice.util;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomRestTemplate {

    public RestTemplate createRestTemplate(int setTimeout) {
        return new RestTemplate(getClientHttpRequestFactory(setTimeout));
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory(int setTimeout) {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(setTimeout);
        clientHttpRequestFactory.setReadTimeout(setTimeout);
        return clientHttpRequestFactory;
    }
}
