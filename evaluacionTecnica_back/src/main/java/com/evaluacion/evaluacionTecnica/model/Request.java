package com.evaluacion.evaluacionTecnica.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Request {

    @Getter
    @Setter
    private DebitPerson debitPerson;

    @Getter
    @Setter
    private Number amount;


}
