package com.springKafka.consumer;

import com.springKafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "Users", groupId = "UserConsume-Group", containerFactory = "userConcurrentKafkaListenerContainerFactory")
    public void userConsume(@Header (KafkaHeaders.RECEIVED_PARTITION_ID) int partition ,
                            @Header (KafkaHeaders.OFFSET) int offset,
                            @Header (KafkaHeaders.RECEIVED_MESSAGE_KEY) String message_key,
                            User user){
        System.out.println("name : "+user.getName());
        System.out.println("age  : "+user.getAge());
        System.out.println("email : "+user.getEmail());
        System.out.println("partition : "+partition);
        System.out.println("offset  : "+offset);
        System.out.println("message_key  : "+message_key);
    }

//    @KafkaListener(topics = "Users", groupId = "UserConsume-Paylod", containerFactory = "normalConcurrentKafkaListenerContainerFactory")
//    public void userPayloadConsume(@Headers String headers, @Payload String paylod){
//        System.out.println("headers : "+headers);
//        System.out.println("paylod : "+paylod);
//    }

}
