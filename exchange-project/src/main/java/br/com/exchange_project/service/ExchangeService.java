package br.com.exchange_project.service;

import br.com.exchange_project.client.ExchangeClient;
import br.com.exchange_project.model.ExchangeRateResponse;
import br.com.exchange_project.model.ExploreQuotationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeClient client;

    @Cacheable(value = "quotationsCache", key = "#sourceCoin.concat(#targetCoin)")
    public ExploreQuotationResponse getQuotationResponse(String sourceCoin, BigDecimal amount, String targetCoin) {
        log.info("[ExploreService - getQuotationResponse]");
        return calculateRates(client.getQuotation(sourceCoin), amount, targetCoin);
    }

    private ExploreQuotationResponse calculateRates(ExchangeRateResponse exchangeRateResponse,
            BigDecimal amount, String targetCoin) {
        log.info("[ExploreService - getRates - the request status: {}]", exchangeRateResponse.getResult());

        var conversionRatesMap = exchangeRateResponse.getConversionRates();
        var baseCode = exchangeRateResponse.getBaseCode();

        var targetValueCoin = conversionRatesMap.get(targetCoin);

        var amountInTargetCoin = conversionRatesMap.get(baseCode)
                .multiply(amount)
                .multiply(targetValueCoin);

        return createExploreQuotationResponse(Map.of(baseCode, amount), Map.of(targetCoin, targetValueCoin),
                targetCoin, amountInTargetCoin);
    }

    private ExploreQuotationResponse createExploreQuotationResponse(Map<String, BigDecimal> currentCurrencyMap,
            Map<String, BigDecimal> targetCoinMap, String targetCoin, BigDecimal total) {
        log.info("[ExploreService - createExploreQuotationResponse]");
        return new ExploreQuotationResponse(currentCurrencyMap, targetCoinMap, Map.of(targetCoin, total));
    }
}
