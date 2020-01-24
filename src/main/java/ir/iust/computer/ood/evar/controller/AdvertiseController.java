package ir.iust.computer.ood.evar.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import ir.iust.computer.ood.evar.model.Advertise;
import ir.iust.computer.ood.evar.model.QAdvertise;
import ir.iust.computer.ood.evar.repository.AdvertiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/advertise", produces = "application/json")
public class AdvertiseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertiseController.class);
    private final AdvertiseRepository advertiseRepository;
    private final SimpleDateFormat createDateFormat;
    public AdvertiseController(AdvertiseRepository advertiseRepository) {
        this.advertiseRepository = advertiseRepository;
        createDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    }

    @GetMapping()
    public Page<Advertise> getAllAdvertises(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "500") int size
    ) {
        LOGGER.debug("getting all advertises.");
        return advertiseRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
    }

    @GetMapping(path = "/search")
    public Page<Advertise> findMatchedAdvertises(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "500") int size,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "createDate", required = false) String date,
            @RequestParam(name = "price", required = false) Double price,
            @RequestParam(name = "rate", required = false) Integer rate,
            @RequestParam(name = "spec", required = false) String spec,
            @RequestParam(name = "sorter",required = false,defaultValue = "title") String sorter
    ) throws ParseException {
        Predicate predicate = new BooleanBuilder();
        if (title != null && !title.isEmpty()) {
            predicate = QAdvertise.advertise.title.like(title);
        }
        if (date!=null && !date.isEmpty()){
            predicate = QAdvertise.advertise.createDate.after(createDateFormat.parse(date)).and(predicate);
        }
        if (price != null) {
            predicate = QAdvertise.advertise.price.eq(price).and(predicate);
        }
        if (rate != null) {
            predicate = QAdvertise.advertise.rate.eq(rate).and(predicate);
        }
        if (spec != null && !spec.isEmpty()) {
            for (String data : spec.split(",")) {
                List<String> keyValue = Arrays.asList(data.split("="));
                predicate = QAdvertise.advertise.advertiseSpec.get(keyValue.get(0)).eq(keyValue.get(1)).and(predicate);
            }
        }
        return advertiseRepository.findAll(predicate, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sorter)));
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
    public ResponseEntity<Object> getAdvertiseSpecValue(@PathVariable String advertiseId, @PathVariable String key) {
        Advertise advertise = advertiseRepository.findById(advertiseId).orElseThrow(NullPointerException::new);
        return new ResponseEntity<>(advertise.getAdvertiseSpec().get(key), HttpStatus.OK);
    }

    @PostMapping(path = "/advertiseSpec/{advertiseId}/{key}/{value}")
    public ResponseEntity<Advertise> addAdvertiseSpec(@PathVariable String advertiseId, @PathVariable String key, @PathVariable String value) {
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
