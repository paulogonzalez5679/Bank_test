package com.evaluacion.evaluacionTecnica.controller;


import com.evaluacion.evaluacionTecnica.model.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping
    private ResponseEntity user(@Validated @RequestBody Request requestBody) {

        String tokenUrl = "https://desarrollo.gti.fin.ec/boton-web-api-ws-1.0/coopagos/web/public/login";

        User user =  new User();
        user.setUsuario("gti");
        user.setClave("aY9T1MFpCWnwuuuMYe030sEUVG1ZXPe1");

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<User> request = new HttpEntity<>(user, headers);

        // send POST request
        ResponseEntity<Token> response = restTemplate.postForEntity(tokenUrl, request, Token.class);

        Token token = response.getBody();

        String validationUrl = "https://desarrollo.gti.fin.ec/boton-web-api-ws-1.0/coopagos/web/private/validation";

        CreditPerson creditPerson = new CreditPerson();
        creditPerson.setInstitution("2");
        creditPerson.setIdentificationType("C");
        creditPerson.setIdentification("0102514106");
        creditPerson.setAccountType(1);
        creditPerson.setAccount("100004");

        ValidationRequest validationRequest = new ValidationRequest();

        validationRequest.setChanel("ELAKE");
        validationRequest.setDebitPerson(requestBody.getDebitPerson());
        validationRequest.setCreditPerson(creditPerson);
        validationRequest.setAmount(requestBody.getAmount());
        validationRequest.setAmountDelivery("0.00");
        validationRequest.setOrderId("1");
        validationRequest.setOtp("12345");
        validationRequest.setIp("192.168.1.1");

        headers.add("Authorization", token.getToken());

        HttpEntity<ValidationRequest> requestValidation = new HttpEntity<>(validationRequest, headers);

        ResponseEntity<String> responseValidation = restTemplate.postForEntity(validationUrl, requestValidation, String.class);

        return responseValidation;

    }
}
