package com.test.telegram.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.telegram.bots.TestBot;
import com.test.telegram.data.Domain;
import com.test.telegram.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class DomainRequest {

    private final DomainService domainService;
    private final TestBot testBot;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DomainRequest(DomainService domainService, TestBot testBot) {
        this.domainService = domainService;
        this.testBot = testBot;
    }

    // Каждый день получает данные доменов, удаляет прошлодневные данные и записывает в бд новые данные.
    // Далее отправляет рассылку всем пользователям
    @Scheduled(fixedRate = 86400000, initialDelay = 15000)
    public void getDomains() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";
        // Получение данных
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        //Десериализация данных
        Domain[] domainList = objectMapper.readValue(response.getBody(), Domain[].class);

        //Удаление прошлых доменов
        domainService.deleteAll();
        //Сохранение новых доменов
        domainService.saveAll(Arrays.asList(domainList));
        // Рассылка ботом
        testBot.domainMailing(dateFormat.format(new Date()), domainService.count());
    }

}
