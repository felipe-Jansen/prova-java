package br.com.confidencecambio.javabasico;

import br.com.confidencecambio.javabasico.model.DTO.ImcDTO;
import br.com.confidencecambio.javabasico.service.HelloService;
import br.com.confidencecambio.javabasico.service.HealthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class HelloWorldRs {

    private HelloService service;
    private HealthService healthService;

    public HelloWorldRs(
            final HelloService service,
            final HealthService healthService) {

        this.service = service;
        this.healthService = healthService;
    }

    @RequestMapping(value = "/ola-mundo", method = RequestMethod.GET)
    public ResponseEntity<String> olaMundo(@RequestParam(value = "nome", required = false) String nome) {
        var retorno = "Ola " + service.retornaValorValido(nome);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping(value = "/imc")
    public ResponseEntity<ImcDTO> calculaIMc(
            @RequestParam(value = "peso") BigDecimal peso,
            @RequestParam(value = "altura") BigDecimal altura) {
        return new ResponseEntity<>(healthService.calculaImc(peso, altura), HttpStatus.OK);
    }

}
