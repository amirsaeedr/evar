package ir.iust.computer.ood.evar.repository;

import ir.iust.computer.ood.evar.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllBySender_IdAndReceiver_Id(long sender_id, long receiver_id);

    List<Message> findAllByReceiver_IdAndSender_Id(long receiver_id, long sender_id);

}
