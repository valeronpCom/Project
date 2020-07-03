package com.restApp.Controller;

import com.restApp.Models.KeyResponse;
import com.restApp.Models.PostRequestList;
import com.restApp.Models.Request;
import com.restApp.Models.Statistics;
import com.restApp.Repository.RequestRepository;
import com.restApp.Repository.StatisticsRepository;
import com.restApp.Services.Counter;
import com.restApp.Services.KeyCache;
import com.restApp.Services.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController

public class KeyController {

    static KeyResponse response = new KeyResponse();
    List<Integer> list = new ArrayList<>();
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    private KeyCache keyCache;

    @Autowired
    private Counter counter;
    @Async("threadPoolTaskExecutor")
    @PostMapping("/beginning")
    public CompletableFuture<Integer> create(@RequestBody PostRequestList requestList)  {
        counter.increaseCounter();
        Statistics statistics = new Statistics();
        //statistics.setProcessId(counter.getCounter());
        CompletableFuture.runAsync(() -> {
            requestList.getRequests().stream().forEach(request -> {
                Integer number = request.getNumber();
                list.add(number);
                statistics.update(list);
                Request newRequest = new Request();
                newRequest.setNumber(number);
                KeyService service = new KeyService(number);
                response.setAnswerOfGuessing(service.checkNumber());
                newRequest.setAnswer(service.checkNumber());
                requestRepository.save(newRequest);
                keyCache.setValue(number, response);

           });
        statisticsRepository.save(statistics);
        });
    return CompletableFuture.completedFuture(statistics.getId());
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

    @GetMapping(value = "/getStatistics/{Id}")
    public @ResponseBody Statistics getResponseByProcessId(@PathVariable("Id")Integer Id)  {
        Statistics statistics = statisticsRepository.findById(Id);
        return statistics;
    }
}
