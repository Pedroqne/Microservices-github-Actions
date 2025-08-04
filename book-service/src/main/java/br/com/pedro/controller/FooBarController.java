package br.com.pedro.controller;

import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "FooBar Endpoint")
@RestController
@RequestMapping("/book-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    @GetMapping("/foo-bar")
    public String fooBar() {
        logger.info("Request to foo-bar is recived");

        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);

        return response.getBody();
    }

    public String fallbackMethod(Exception e) {
        return "Fallback Method FooBar";
    }
}
