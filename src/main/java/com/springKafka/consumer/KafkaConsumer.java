package com.springKafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springKafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "users", groupId = "UserConsume-Group", containerFactory = "userConcurrentKafkaListenerContainerFactory")
    public void userConsume(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition ,
                            @Header (KafkaHeaders.OFFSET) int offset,
//                            @Header (KafkaHeaders.RECEIVED_MESSAGE_KEY) String message_key,
                            User user
                           ){
        System.out.println("name : "+user.getName());
        System.out.println("age  : "+user.getAge());
        System.out.println("email : "+user.getEmail());
        System.out.println("partition : "+partition);
        System.out.println("offset  : "+offset);
//        System.out.println("message_key  : "+message_key);
    }


    @KafkaListener(topics = "string_key_capital_users", groupId = "UserConsume-Group", containerFactory = "ConcurrentKafkaListenerContainerFactory")
    public void userConsume(@Payload String payload, @Header (KafkaHeaders.RECEIVED_MESSAGE_KEY) String message_key) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(payload);
        User user = mapper.convertValue(obj.get("payload"), User.class);

        System.out.println("name : "+user.getName());
        System.out.println("age  : "+user.getAge());
        System.out.println("email : "+user.getEmail());

    }
}
