package rabbitmq.tutorial.t06;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RpcServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);// autoAck fasle

        System.out.println(" [x] Awaiting RPC requests");
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            BasicProperties props = delivery.getProperties();
            BasicProperties replyProps = new BasicProperties()
                    .builder()
                    .correlationId(props.getCorrelationId())
                    .build();

            String message = new String(delivery.getBody());
            int n = Integer.parseInt(message);
            System.out.println(" [.] fib(" + message + ")");
            String response = "" + fib(n);

            channel.basicPublish("", props.getReplyTo(), replyProps, response.getBytes());
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    private static int fib(int n) throws Exception {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

}
