package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.exception.ImcInvalidParams;
import br.com.confidencecambio.javabasico.model.DTO.ImcDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

@Service
public class HealthService {

    public ImcDTO calculaImc(BigDecimal peso, BigDecimal altura) {
        ImcDTO imcDTO = new ImcDTO();

        if(Objects.equals(peso, BigDecimal.ZERO) || Objects.equals(altura, BigDecimal.ZERO)) {
            throw new ImcInvalidParams();
        }

        imcDTO.setValor(peso.divide(altura.pow(2), MathContext.DECIMAL32).setScale(2, RoundingMode.FLOOR));
        return imcDTO;
    }


}
