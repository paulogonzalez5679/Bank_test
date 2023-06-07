package com.evaluacion.evaluacionTecnica.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class User {

    @Getter
    @Setter
    private String usuario;

    @Getter
    @Setter
    private String clave;

}
