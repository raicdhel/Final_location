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
    public void wheneverDelivered_ChangeStatus(@Payload Delivered delivered){

        if(delivered.isMe()){
            Location location = new Location();
            location.setOrderId(delivered.getOrderId());
            location.setNowStatus(delivered.getDeliveryStatus());

            if(delivered.getDeliveryStatus().equals("Delivered")) {
                location.setDesc("Your Pizza arrived !!!");
            } else {
                location.setDesc("Order and Delivery is " + delivered.getDeliveryStatus());
            }

            locationRepository.save(location);

            System.out.println("##### listener ChangeStatus : " + delivered.toJson());
        }
    }

}
