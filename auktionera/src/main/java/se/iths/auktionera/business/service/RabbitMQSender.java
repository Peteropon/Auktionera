package se.iths.auktionera.business.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${auktionera.rabbitmq.exchange}")
    private String exchange;

    @Value("${auktionera.rabbitmq.routingkey}")
    private String routingkey;

/*    @Scheduled
    public void send(String message, String userName) {
        String CustomMessage = "New user has logged in from auktionera" + message + userName;

        rabbitTemplate.convertAndSend(exchange, routingkey, CustomMessage);
        System.out.println("Send message to consumer= " + CustomMessage + "");
    }*/

    @Scheduled
    public void send(String userName, Long id) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("userName", userName);
        obj.put("id", id);

        rabbitTemplate.convertAndSend(exchange, routingkey, obj);
        System.out.println("Send message to consumer= " + userName + "" + " id: " + id);
    }
}
