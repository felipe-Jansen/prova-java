package br.com.confidencecambio.javabasico.model;

import org.junit.jupiter.api.Test;

class RoboTest {

    Robo robo;

    @Test
    void setNome() {
        this.robo = new Robo();
        robo.setName(null);
    }

}
