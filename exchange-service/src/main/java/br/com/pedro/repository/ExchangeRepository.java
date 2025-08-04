package br.com.pedro.repository;

import br.com.pedro.models.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange,Long> {

    public Exchange findByFromAndTo(String from, String to);
}
