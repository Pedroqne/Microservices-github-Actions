package br.com.pedro.proxy;

import br.com.pedro.dto.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "exchange-service")
public interface ExchangeProxy {

    @GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
    public Exchange getExchange(
            @PathVariable("amount") Double amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to);

}

