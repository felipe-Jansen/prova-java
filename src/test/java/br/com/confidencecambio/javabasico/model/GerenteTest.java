package br.com.confidencecambio.javabasico.model;

import org.junit.jupiter.api.Test;

class GerenteTest {

    Gerente gerente;

    @Test
    void setNome() {
        this.gerente = new Gerente();
        gerente.setName("     ");
    }

}
