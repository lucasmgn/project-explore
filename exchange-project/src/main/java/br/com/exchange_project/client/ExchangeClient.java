package br.com.exchange_project.client;

import br.com.exchange_project.model.ExchangeRateResponse;
import br.com.exchange_project.util.ExchangeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeClient {

    private final ExchangeProperties exchangeProperties;
    private final RestClient restClient = RestClient.create();

    public ExchangeRateResponse getQuotation(String sourceCoin) {
        log.info("[ExchangeClient - Request for external exchangeAPI]");
        return restClient
                .get()
                .uri(exchangeProperties.getUrl().concat(sourceCoin))
                .retrieve()
                .body(ExchangeRateResponse.class);
    }
}
