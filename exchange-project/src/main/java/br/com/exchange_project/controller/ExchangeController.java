package br.com.exchange_project.controller;
import br.com.exchange_project.model.ExploreQuotationResponse;
import br.com.exchange_project.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService service;

    @GetMapping("/explore/quotation")
    public ResponseEntity<ExploreQuotationResponse> getQuotation(@RequestParam String sourceCoin,
            @RequestParam BigDecimal amount, @RequestParam String targetCoin) {
        return ResponseEntity.ok(service.getQuotationResponse(sourceCoin, amount, targetCoin));
    }
}
