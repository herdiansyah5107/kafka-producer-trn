package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    // Materi Traning
    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable String message) {

        // Sending the message
        kafkaTemplate.send("MyTopic", message);
        return "Published Successfully";
    }

    // ===============================================================================
    // ===============================================================================
    // ===============================================================================
    // ===============================================================================
    // Exercise Day 5
    //untuk mengirim ke consumer
    @GetMapping("/ping")
    public String ping(String messagePing) {
        messagePing = "ping";
        // Sending the message
        kafkaTemplate.send("ping-request", messagePing);
        return "Published Successfully Ping";
    }

    //Untuk Menerima Message consumer
    @KafkaListener(topics = "pong-request", groupId = "group_id")
    // Method
    public void consumePong(String messagePong) {
        // Print statement
        System.out.println("message = " + messagePong);
    }
}
