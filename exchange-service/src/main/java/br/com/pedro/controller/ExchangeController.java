package br.com.pedro.controller;

import br.com.pedro.environment.InstanceInformationService;
import br.com.pedro.models.Exchange;
import br.com.pedro.repository.ExchangeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Exchange Endpoint")
@RestController
@RequestMapping("exchange-service")
public class ExchangeController {


    @Autowired
    InstanceInformationService informationService;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Operation(summary = "Get an exchage from amount of currency")
    @GetMapping(value = "{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to) {


    Exchange exchange = exchangeRepository.findByFromAndTo(from, to);

    if (exchange == null) {throw new RuntimeException("Exchange not found");}

    BigDecimal conversionFactor = exchange.getConversionFactor();

    BigDecimal convertedValue = conversionFactor.multiply(amount);
    exchange.setConvertedValue(convertedValue);
    exchange.setEnvironment(informationService.retrieveServicePort());

    return exchange;
    }
}
