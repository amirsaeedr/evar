package ir.iust.computer.ood.evar.Service.Imp;

import ir.iust.computer.ood.evar.Service.StringService;
import ir.iust.computer.ood.evar.Service.UserService;
import ir.iust.computer.ood.evar.model.User;
import ir.iust.computer.ood.evar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringService stringService;

    @Override
    public User save(String username,
                     String password,
                     String name,
                     String lastname,
                     int phone,
                     String adderss,
                     int postalcode) {
        String passwordBeCrypt = stringService.encodeBase64(password);
        String uuid = stringService.set_String();

        User user = new User(username, passwordBeCrypt, name, lastname, phone, adderss, postalcode, uuid, null);
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public User find(long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User login(String username, String password) throws Exception {
        User userFounded = userRepository.findByUsername(username);
        if (userFounded == null) {
            throw new Exception();
        }
        String rightPass = stringService.decodeBase64(userFounded.getPassword());
        if (!rightPass.equals(password)) {
            throw new Exception();
        }
        userFounded.setToken(stringService.encodeBase64(userFounded.getUuid()));
        userRepository.save(userFounded);
        return userFounded;
    }
}
