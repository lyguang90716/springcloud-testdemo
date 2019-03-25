package com.consumer.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://API-GATEWAY-ZUUL/api-hi/hi/" + name, String.class);
    }

    public String hiError(String name) {
        return "hey " +
                name + ", there is some problem with hi page";
    }

}
