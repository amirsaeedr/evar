package ir.iust.computer.ood.evar.controller;

import ir.iust.computer.ood.evar.model.Advertise;
import ir.iust.computer.ood.evar.repository.AdvertiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/advertise", produces = "application/json")
public class AdvertiseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiseController.class);
    private final AdvertiseRepository advertiseRepository;

    public AdvertiseController(AdvertiseRepository advertiseRepository) {
        this.advertiseRepository = advertiseRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Advertise>> getAllAdvertises() {
        LOGGER.debug("getting all advertises.");
        return new ResponseEntity<>(advertiseRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{advertiseId}")
    public ResponseEntity<Advertise> getAdvertise(@PathVariable String advertiseId) {
        LOGGER.debug("getting advertise with id: {}", advertiseId);
        return new ResponseEntity<>(advertiseRepository.findById(advertiseId).orElseThrow(NullPointerException::new), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Advertise> addAdvertise(@RequestBody Advertise advertise) {
        LOGGER.debug("saving advertise.");
        return new ResponseEntity<>(advertiseRepository.save(advertise), HttpStatus.CREATED);
    }

    @GetMapping(path = "/advertiseSpec/{advertiseId}")
    public ResponseEntity<Object> getAllAdvertiseSpec(@PathVariable String advertiseId) {
        Advertise advertise = advertiseRepository.findById(advertiseId).orElseThrow(NullPointerException::new);
        return new ResponseEntity<>(advertise.getAdvertiseSpec(), HttpStatus.OK);
    }

    @GetMapping(path = "/advertiseSpec/{advertiseId}/{key}")
    public ResponseEntity<Object> getAllAdvertiseSpec(@PathVariable String advertiseId, @PathVariable String key) {
        Advertise advertise = advertiseRepository.findById(advertiseId).orElseThrow(NullPointerException::new);
        return new ResponseEntity<>(advertise.getAdvertiseSpec().get(key), HttpStatus.OK);
    }

    @PostMapping(path = "/advertiseSpec/{advertiseId}/{key}/{value}")
    public ResponseEntity<Advertise> getAllAdvertiseSpec(@PathVariable String advertiseId, @PathVariable String key, @PathVariable String value) {
        Advertise advertise = advertiseRepository.findById(advertiseId).orElseThrow(NullPointerException::new);
        advertise.getAdvertiseSpec().put(key, value);
        return new ResponseEntity<>(advertiseRepository.save(advertise), HttpStatus.OK);
    }

    @PutMapping(path = "/update/{advertiseId}")
    public ResponseEntity<Advertise> updateAdvertise(@PathVariable String advertiseId, @RequestBody Advertise advertise) {
        advertiseRepository.deleteById(advertiseId);
        return new ResponseEntity<>(advertiseRepository.save(advertise), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteAllAdvertise() {
        advertiseRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{advertiseId}")
    public ResponseEntity<String> deleteAdvertise(@PathVariable String advertiseId) {
        advertiseRepository.deleteById(advertiseId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
