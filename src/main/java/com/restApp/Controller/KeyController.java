package com.restApp.Controller;

import com.restApp.Exceptions.BadRequestException;
import com.restApp.Exceptions.InternalException;
import com.restApp.Models.KeyResponse;
import com.restApp.Services.Counter;
import com.restApp.Services.KeyCache;
import com.restApp.Services.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class KeyController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KeyCache keyCache;

    @Autowired
    private Counter counter;

    @GetMapping("/beginning")
    public KeyResponse beginning(@RequestParam(value = "number") Integer number) throws BadRequestException, InternalException {
        counter.increaseCounter();
        logger.info("Check entered data");
        if(number == null){
            logger.error("Parameters were not entered correctly or were not entered at all.");
            throw new BadRequestException(400, "Bad request exception");
        }
        if (number == 6 || number == 7){
            logger.error("You've just entered some values that are not possible to be entered");
            throw new InternalException(500, "Internal exception: something wrong with entered values");
        }
        if (keyCache.checkForExistingInCache(number)) {
            logger.info("The value has been taken from cache.");
            return keyCache.getValue(number);
        }
        logger.info("The value is fine and has not been taken from cache.");
        KeyService service = new KeyService(number);
        KeyResponse response = new KeyResponse();
        response.setAnswerOfGuessing(service.checkNumber());
        keyCache.setValue(number, response);
        return response;
    }

    @RequestMapping("/counter")
    public Counter getCounter(){
        return counter;
    }

}