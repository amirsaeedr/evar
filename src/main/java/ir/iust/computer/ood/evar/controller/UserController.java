package ir.iust.computer.ood.evar.controller;

import ir.iust.computer.ood.evar.Service.UserService;
import ir.iust.computer.ood.evar.util.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/find", method = RequestMethod.GET)
  public ResponseEntity find(@RequestBody long id) {
    return ResponseHelper.response(userService.find(id));
  }

  @RequestMapping(value = "/get-colleague/{id}", method = RequestMethod.GET)
  public ResponseEntity findcolleague(@RequestBody long id) {
    return ResponseHelper.response(userService.find(id));
  }

  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll() {
    return ResponseHelper.response(userService.getAll());
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity save(@RequestBody GetUser getUser) {
    return ResponseHelper.response(userService.save(
            getUser.getUsername(),
            getUser.getPassword(),
            getUser.getName(),
            getUser.getLastname(),
            getUser.getPhone(),
            getUser.getAdderss(),
            getUser.getPostalcode()
    ));
  }

  @RequestMapping(value = "/login")
  public ResponseEntity login(@RequestBody GetUser getUser) throws Exception {
    return ResponseHelper.response(
            userService.login(
                    getUser.getUsername(),
                    getUser.getPassword()));
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity delete(@RequestBody long id) {
    userService.delete(id);
    return ResponseHelper.response(true);
  }



}
