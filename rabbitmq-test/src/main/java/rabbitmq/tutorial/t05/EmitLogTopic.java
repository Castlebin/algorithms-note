package rabbitmq.tutorial.t05;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class EmitLogTopic {
    private static final String TOPIC_EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the routingKey: ");
        String routingKey = scanner.nextLine();
        String message;
        while (!(message = scanner.nextLine()).equals("exit")) {
            channel.basicPublish(TOPIC_EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] sent message: " + message);
        }

        channel.close();
        connection.close();
    }

}
