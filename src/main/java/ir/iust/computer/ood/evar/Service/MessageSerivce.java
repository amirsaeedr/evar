package ir.iust.computer.ood.evar.Service;

import ir.iust.computer.ood.evar.model.Message;
import ir.iust.computer.ood.evar.model.User;

import java.util.List;

public interface MessageSerivce {

    Message save(User sender,
                 String msg,
                 User receiver);

    List<Message> find(long sender_id, long receiver_id);

}
