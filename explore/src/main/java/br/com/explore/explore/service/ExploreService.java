package br.com.explore.explore.service;

import br.com.explore.explore.client.ExchangeClient;
import br.com.explore.explore.model.exchange.ExchangeRateResponse;
import br.com.explore.explore.model.response.ExploreQuotationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/*
    Colocar um método com o redis para fazer a request da api e armazenar o valor em cache, sempre no time_next_update_unix
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ExploreService {

    private final ExchangeClient client;

    public ExploreQuotationResponse getQuotationResponse(String sourceCoin, BigDecimal amount, String targetCoin) {
        log.info("[ExploreService - getQuotationResponse]");

        return getRates(client.getQuotation(sourceCoin), amount, targetCoin);
    }

    public void a() {
        
    }

    private ExploreQuotationResponse getRates(ExchangeRateResponse exchangeRateResponse,
            BigDecimal amount, String targetCoin) {
        log.info("[ExploreService - getRates - the request status: {}]", exchangeRateResponse.getResult());

        var conversionRatesMap = exchangeRateResponse.getConversionRates();
        var baseCode = exchangeRateResponse.getBaseCode();

        var currentValueCoin = conversionRatesMap.get(baseCode);
        var targetValueCoin = conversionRatesMap.get(targetCoin);

        var amountInTargetCoin = currentValueCoin
                .multiply(amount)
                .multiply(targetValueCoin);

        return createExploreQuotationResponse(Map.of(baseCode, amount), Map.of(targetCoin, targetValueCoin),
                targetCoin, amountInTargetCoin);
    }

    private ExploreQuotationResponse createExploreQuotationResponse(Map<String, BigDecimal> currentCurrencyMap,
            Map<String, BigDecimal> targetCoinMap, String targetCoin, BigDecimal total) {
        log.info("[ExploreService - createExploreQuotationResponse]");

        var exploreQuotationResponse = new ExploreQuotationResponse();
        exploreQuotationResponse.setTargetCoin(targetCoinMap);
        exploreQuotationResponse.setCurrentCurrency(currentCurrencyMap);
        exploreQuotationResponse.setTotalQuote(Map.of(targetCoin, total));

        return exploreQuotationResponse;
    }
}
