package com.springKafka.controller;


import com.springKafka.avromodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    private static final String TOPIC = "UsersRegistry";


    @PostMapping("/publish")
    public String post(@RequestBody User user){
        //only value
        //userKafkaTemplate.send(TOPIC, user);
        //with key
        userKafkaTemplate.send(TOPIC, "oy", user);
        return "User is registered !!";
    }
}
