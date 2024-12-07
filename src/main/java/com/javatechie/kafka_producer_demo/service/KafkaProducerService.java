package com.javatechie.kafka_producer_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future =  kafkaTemplate.send("test-topic2",message);
        future.whenComplete((result,ex)->{
            if(ex == null){
                System.out.println("Sent Message=["+message+"] with offset=["+result.getRecordMetadata().offset()+"]");
            }else{
                System.out.println("Unable to send the message=["+message+"] due to error=["+ex.getMessage()+"]");
            }
        });
    }
}
