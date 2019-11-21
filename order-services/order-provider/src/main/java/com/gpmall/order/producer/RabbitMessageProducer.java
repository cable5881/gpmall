package com.gpmall.order.producer;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author： wz
 * @Date: 2019-09-18 00:52
 **/
@Component
public class RabbitMessageProducer {

	//@Autowired
	//RabbitTemplate rabbitTemplate;
    //
	//public void send(String context) {
	//	//将订单发送到rabbitmq
	//	rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE, context, message -> {
	//		message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
	//		//毫秒为单位
	//		message.getMessageProperties().setDelay(10 * 60 * 1000);
	//		return message;
	//	});
	//}
}