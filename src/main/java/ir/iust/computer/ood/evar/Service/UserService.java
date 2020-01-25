package ir.iust.computer.ood.evar.Service;

import ir.iust.computer.ood.evar.model.User;

import java.util.List;

public interface UserService {
    User save(
            String username,
            String password,
            String name,
            String lastname,
            int phone,
            String adderss,
            int postalcode
    );

    void delete(long id);

    User find(long id);

    List<User> getAll();

    User login(String username, String password) throws Exception;
}
