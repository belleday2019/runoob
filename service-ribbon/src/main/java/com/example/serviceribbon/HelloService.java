package com.example.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiback")
    public String hiService(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name="+name,String.class);
    }

    public String hiback(String name){
        return "hi,"+name+",sorry,error!";
    }
}
