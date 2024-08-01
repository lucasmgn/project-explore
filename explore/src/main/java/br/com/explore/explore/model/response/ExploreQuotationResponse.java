package br.com.explore.explore.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
public class ExploreQuotationResponse {

    @JsonProperty("current_currency")
    private Map<String, BigDecimal> currentCurrency;

    @JsonProperty("desired_currency")
    private Map<String, BigDecimal> targetCoin;

    @JsonProperty("total_quote")
    private Map<String, BigDecimal> totalQuote;

}
