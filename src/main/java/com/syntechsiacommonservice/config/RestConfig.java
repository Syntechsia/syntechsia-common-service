package com.syntechsiacommonservice.config;

import com.syntechsiacommonservice.model.request.EmailRequest;
import com.syntechsiacommonservice.model.response.EmailResponse;
import com.syntechsiacommonservice.util.CustomRestTemplate;
import com.syntechsiacommonservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

@Configuration
@Slf4j
public class RestConfig {

    @Value("${base.url}")
    private String baseUrl;
    @Value("${default.timeout}")
    private int defaultTimeout;

    private final CustomRestTemplate customRestTemplate;

    @Autowired
    public RestConfig(CustomRestTemplate customRestTemplate) {
        this.customRestTemplate = customRestTemplate;
    }

    public Object sendEmail(EmailRequest request) {
        try {
            log.info("start send email with request {}", JsonUtil.getString(request));
            String url = baseUrl.concat("/syntechsia/helper/api/send/email");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> httpEntity = new HttpEntity<>(request, headers);


            ResponseEntity<EmailResponse> responseEntity = customRestTemplate.createRestTemplate(defaultTimeout)
                    .exchange(url, HttpMethod.POST, httpEntity, EmailResponse.class);
            log.info(" end send email with response {}", JsonUtil.getString(responseEntity.getBody()));
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.error("error send email with response {}", e.getResponseBodyAsString());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("error send email : ", ex);
            return null;
        }
    }
}
