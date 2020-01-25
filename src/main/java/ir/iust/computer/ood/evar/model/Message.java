package ir.iust.computer.ood.evar.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User sender;

    @Column(name = "message", columnDefinition = "text")
    private String msg;

    @OneToOne
    private User receiver;

    @Column(name = "time")
    private LocalDateTime time;

    public Message() {
    }

    public Message(User sender, String msg, User receiver, LocalDateTime now) {
        this.sender = sender;
        this.msg = msg;
        this.receiver = receiver;
        this.time =  now;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
