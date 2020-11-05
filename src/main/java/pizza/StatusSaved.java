package pizza;

public class StatusSaved extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String nowStatus;

    public StatusSaved(){
        super();
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
}
