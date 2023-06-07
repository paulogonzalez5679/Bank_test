package com.evaluacion.evaluacionTecnica.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ValidationRequest {

    @Getter
    @Setter
    private String chanel;

    @Getter
    @Setter
    private DebitPerson debitPerson;

    @Getter
    @Setter
    private CreditPerson creditPerson;

    @Getter
    @Setter
    private Number amount;

    @Getter
    @Setter
    private String amountDelivery;

    @Getter
    @Setter
    private String orderId;

    @Getter
    @Setter
    private String otp;

    @Getter
    @Setter
    private String ip;

}
