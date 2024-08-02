package com.reactive.kafka.components;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Consumer {
    private KafkaConsumer<String,String> consumer;

    public Consumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "grupoA");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
    }

    public void suscribir(String topic){
        consumer.subscribe(List.of(topic));
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
            for(ConsumerRecord<String, String> record: records){
                System.out.println(record.value());
            }
        }
    }

}
