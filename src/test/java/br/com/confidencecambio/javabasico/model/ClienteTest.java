package br.com.confidencecambio.javabasico.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

    Cliente cliente;

    @BeforeEach
    void setNome() {
        this.cliente = new Cliente();
        cliente.setName("      felipe de  neiva     ribeiro        jansen         ");
    }

    @Test
    void getNome() {
        Assertions.assertEquals("felipe de neiva ribeiro jansen", cliente.getName());
    }

    @Test
    void getNameCapitalized() {
        Assertions.assertEquals("Felipe De Neiva Ribeiro Jansen", this.cliente.getNameCapitalized());
    }

    @Test
    void getNameUppercase() {
        Assertions.assertEquals("FELIPE DE NEIVA RIBEIRO JANSEN", this.cliente.getNameUppercase());
    }

    @Test
    void getFirstName() {
        Assertions.assertEquals("felipe", this.cliente.getFirstName());
    }

    @Test
    void getLastName() {
        Assertions.assertEquals("de neiva ribeiro jansen", this.cliente.getLastName());
    }

    @Test
    void getAbbreviatedName() {
        Assertions.assertEquals("Felipe N. R. Jansen", this.cliente.getAbbreviatedName());
    }

}
