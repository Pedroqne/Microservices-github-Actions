package br.com.pedro.controller;

import br.com.pedro.dto.Exchange;
import br.com.pedro.model.Book;
import br.com.pedro.proxy.ExchangeProxy;
import br.com.pedro.repository.BookRepository;
import br.com.pedro.enviroment.InstanceInformationServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Tag(name = "Book Endpoint")
@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private InstanceInformationServer informationServer;

    @Autowired
    private ExchangeProxy exchangeProxy;

    @Operation(summary = "Find a specificy book by your ID")
    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(@PathVariable Long id, @PathVariable String currency) {

        var book = bookRepository.findById(id).orElseThrow();

        var port = informationServer.getPort();


        Exchange exchange = exchangeProxy.getExchange(book.getPrice(), "USD", currency);

        book.setCurrency(currency);
        book.setPrice(exchange.getConvertedValue());
        book.setEnviroment("BOOK PORT: " + port + " EXCHANGE PORT: " + exchange.getEnvironment() );

        return book;
    }
}
