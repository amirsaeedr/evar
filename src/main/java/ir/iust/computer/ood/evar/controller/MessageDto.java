package ir.iust.computer.ood.evar.controller;

import ir.iust.computer.ood.evar.model.User;

public class MessageDto {

    private User sender;

    private String msg;

    private User receive;

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

    public User getReceive() {
        return receive;
    }

    public void setReceive(User receive) {
        this.receive = receive;
    }
}
