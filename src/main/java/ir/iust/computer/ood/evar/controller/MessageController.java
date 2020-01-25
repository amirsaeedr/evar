package ir.iust.computer.ood.evar.controller;


import ir.iust.computer.ood.evar.Service.MessageSerivce;
import ir.iust.computer.ood.evar.util.helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageSerivce messageSerivce;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MessageDto messageDto) {
        return ResponseHelper.response(messageSerivce.save(messageDto.getSender(), messageDto.getMsg(), messageDto.getReceive()));
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity find(@RequestBody MessageDto messageDto) {
        return ResponseHelper.response(messageSerivce.find(messageDto.getSender().getId(), messageDto.getReceive().getId()));
    }


}
