package com.miaosha.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {
    public QueueConsumer(String endpointName) {
        super(endpointName);
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);
        System.out.println("Message Number " + map.get("message number") + " received.");
    }

    @Override
    public void run() {
        // start consuming messages. Auto acknowledge messages.
        try {
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
