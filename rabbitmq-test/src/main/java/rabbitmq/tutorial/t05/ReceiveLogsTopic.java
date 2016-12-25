package rabbitmq.tutorial.t05;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsTopic {
    private static final String TOPIC_EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");

        String queueName = channel.queueDeclare().getQueue();

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the routingKey: ");
        String routingKey = scanner.nextLine();
        channel.queueBind(queueName, TOPIC_EXCHANGE_NAME, routingKey);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println("[x] received message: " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
