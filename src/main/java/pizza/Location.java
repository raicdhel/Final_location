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
        StatusSaved statusSaved = new StatusSaved();
        BeanUtils.copyProperties(this, statusSaved);
        statusSaved.publishAfterCommit();


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
