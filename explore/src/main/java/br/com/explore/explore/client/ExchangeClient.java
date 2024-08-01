package br.com.explore.explore.client;

import br.com.explore.explore.model.exchange.ExchangeRateResponse;
import br.com.explore.explore.util.ExchangeProperties;
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

    public ExchangeRateResponse getQuotation(String sourceCoin){
        return restClient
                .get()
                .uri(exchangeProperties.getUrl().concat(sourceCoin))
                .retrieve()
                .body(ExchangeRateResponse.class);
    }
}
