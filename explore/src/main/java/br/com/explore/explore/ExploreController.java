package br.com.explore.explore;

import br.com.explore.explore.model.response.ExploreQuotationResponse;
import br.com.explore.explore.service.ExploreService;
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
public class ExploreController {

    private final ExploreService service;

    @GetMapping("/explore/quotation")
    public ResponseEntity<ExploreQuotationResponse> getQuotation(@RequestParam String sourceCoin,
            @RequestParam BigDecimal amount, @RequestParam String targetCoin) {
        return ResponseEntity.ok(service.getQuotationResponse(sourceCoin, amount, targetCoin));
    }
}
