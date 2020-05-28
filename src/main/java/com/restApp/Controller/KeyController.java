package com.restApp.Controller;

import com.restApp.Exceptions.BadRequestException;
import com.restApp.Exceptions.InternalException;
import com.restApp.Models.KeyResponse;
import com.restApp.Models.PostRequest;
import com.restApp.Models.Request;
import com.restApp.Models.Statistics;
import com.restApp.Repository.RequestRepository;
import com.restApp.Services.Counter;
import com.restApp.Services.KeyCache;
import com.restApp.Services.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class KeyController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Statistics statistics = new Statistics();

    static KeyResponse response = new KeyResponse();

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    private KeyCache keyCache;

    @Autowired
    private Counter counter;

    @PostMapping("/beginning")
    public ResponseEntity<?> create(@RequestBody PostRequest request) throws BadRequestException, InternalException {
        Integer number = Integer.valueOf(request.getNumber());
        counter.increaseCounter();
        logger.info("Check entered data");
        if (number == null) {
            logger.error("Parameters were not entered correctly or were not entered at all.");
            throw new BadRequestException(400, "Bad request exception");

        }
        if (number == 6 || number == 7) {
            logger.error("You've just entered some values that are not possible to be entered");
            throw new InternalException(500, "Internal exception: something wrong with entered values");

        }
        statistics.getResponse().add(number);
        statistics.update();
        if (keyCache.checkForExistingInCache(number)) {
            logger.info("The value has been taken from cache.");
            response = keyCache.getValue(number);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        logger.info("The value is fine and has not been taken from cache.");
        Request newRequest = new Request();
        newRequest.setNumber(number);
        KeyService service = new KeyService(number);
        response.setAnswerOfGuessing(service.checkNumber());
        newRequest.setAnswer(service.checkNumber());
        requestRepository.save(newRequest);
        keyCache.setValue(number, response);
        return new ResponseEntity<>(HttpStatus.CREATED);
}

    @GetMapping("/beginning")
    public ResponseEntity<KeyResponse> beginning() throws BadRequestException, InternalException {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    @RequestMapping("/counter")
    public Counter getCounter() {
        return counter;
    }

    @RequestMapping("/statistics")
    public Statistics getStatistics() {
        return statistics;
    }
}
