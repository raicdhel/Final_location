package pizza;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Location_table")
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String nowStatus;
    private String desc;

    @PostPersist
    public void onPostPersist(){
        LocationSaved locationSaved = new LocationSaved();
        BeanUtils.copyProperties(this, locationSaved);
        locationSaved.publishAfterCommit();

/*
        try {
            Thread.sleep((long) (400 + Math.random() * 300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

        System.out.println("================ time ===============");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getNowStatus() {
        return nowStatus;
    }

    public void setNowStatus(String nowStatus) {
        this.nowStatus = nowStatus;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }




}
