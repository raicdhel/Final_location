package pizza;

import pizza.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    LocationRepository locationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_ChangeStatus(@Payload Ordered ordered){

        if(ordered.isMe()){
            Location location = new Location();
            location.setOrderId(ordered.getId());
            location.setNowStatus(ordered.getOrderStatus());

            if(ordered.getOrderStatus().equals("Ordered")) {
                location.setDesc(ordered.getPizzaId() + " Pizza " + ordered.getQty() + "ea ordered !!!");
            } else {
                location.setDesc("Your Order is " + ordered.getOrderStatus());
            }

            locationRepository.save(location);

            System.out.println("##### listener ChangeStatus : " + ordered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_ChangeStatus(@Payload Paid paid){

        if(paid.isMe()){
            Location location = new Location();
            location.setOrderId(paid.getOrderId());
            location.setNowStatus(paid.getPaymentStatus());

            if(paid.getPaymentStatus().equals("Paid")) {
                location.setDesc("Your credits card was paid 10,000 won !!!");
            } else {
                location.setDesc("Your Payment is " + paid.getPaymentStatus());
            }

            locationRepository.save(location);

            System.out.println("##### listener ChangeStatus : " + paid.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDelivered_ChangeStatus(@Payload Delivered delivered){

        if(delivered.isMe()){
            Location location = new Location();
            location.setOrderId(delivered.getOrderId());
            location.setNowStatus(delivered.getDeliveryStatus());

            if(delivered.getDeliveryStatus().equals("Delivered")) {
                location.setDesc("Your Pizza is 800m left !!!");
            } else {
                location.setDesc("Order and Delivery is " + delivered.getDeliveryStatus());
            }

            locationRepository.save(location);

            System.out.println("##### listener ChangeStatus : " + delivered.toJson());
        }
    }

}
