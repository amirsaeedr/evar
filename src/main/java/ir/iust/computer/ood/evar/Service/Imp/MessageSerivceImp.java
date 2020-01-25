package ir.iust.computer.ood.evar.Service.Imp;

import ir.iust.computer.ood.evar.Service.MessageSerivce;
import ir.iust.computer.ood.evar.model.Message;
import ir.iust.computer.ood.evar.model.User;
import ir.iust.computer.ood.evar.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSerivceImp implements MessageSerivce {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(User sender, String msg, User receiver) {

        Message message = new Message(sender,msg,receiver, LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> find(long sender_id, long receiver_id) {

        List<Message> SendMsg = messageRepository.findAllBySender_IdAndReceiver_Id(sender_id, receiver_id);
        List<Message> getMsg = messageRepository.findAllByReceiver_IdAndSender_Id(receiver_id, sender_id);
        List<Message> AllMsg = new ArrayList<>();

        for (Message message : SendMsg) {
            AllMsg.add(message);
        }
        for (Message message : getMsg) {
            AllMsg.add(message);
        }

        return AllMsg;
    }
}
