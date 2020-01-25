package ir.iust.computer.ood.evar.repository;


import ir.iust.computer.ood.evar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);
}
