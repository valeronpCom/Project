package com.restApp.Controller;

import com.restApp.Models.*;
import com.restApp.Repository.RequestRepository;
import com.restApp.Services.Counter;
import com.restApp.Services.KeyCache;
import com.restApp.Services.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class KeyController {

    Statistics statistics = new Statistics();

    static KeyResponse response = new KeyResponse();

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    private KeyCache keyCache;

    @Autowired
    private Counter counter;

    @PostMapping("/beginning")
    public Statistics create(@RequestBody PostRequestList requestList)  {

        requestList.getRequests().stream().forEach(request -> {
            counter.increaseCounter();
            Integer number = request.getNumber();
            statistics.getResponse().add(number);
            statistics.update();
            Request newRequest = new Request();
            newRequest.setNumber(number);
            KeyService service = new KeyService(number);
            response.setAnswerOfGuessing(service.checkNumber());
            newRequest.setAnswer(service.checkNumber());
            requestRepository.save(newRequest);
            keyCache.setValue(number, response);

        });
        return statistics;
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
