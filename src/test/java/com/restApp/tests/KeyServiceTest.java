package com.restApp.tests;

import com.restApp.Services.KeyService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KeyServiceTest{
    @Test
    public void checkNumber() {
        KeyService service = new KeyService(3);
        String message = "Введённое числе меньше загаданного";
        assertEquals(service.checkNumber(), message);
    }
}