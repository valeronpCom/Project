package com.restApp;

import Exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/beginning")
    public KeyResponse beginning(@RequestParam(value = "number") Integer number) throws BadRequestException, InternalException {
        logger.info("Check entered data");
        if(number == null){
            logger.error("Parameters were not entered correctly or were not entered at all.");
            throw new BadRequestException(400, "Bad request exception");
        }
        if (number == 6 || number == 7){
            logger.error("You've just entered some values that are not possible to be entered");
            throw new InternalException(500, "Internal exception: something wrong with entered values");
        }
        KeyService service = new KeyService(number);
        KeyResponse response = new KeyResponse();
        response.setAnswerOfGuessing(service.checkNumber());
        return response;

    }
}
