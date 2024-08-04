package br.com.exchange_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExploreQuotationResponse implements Serializable {

    @JsonProperty("current_currency")
    private Map<String, BigDecimal> currentCurrency;

    @JsonProperty("desired_currency")
    private Map<String, BigDecimal> targetCoin;

    @JsonProperty("total_quote")
    private Map<String, BigDecimal> totalQuote;
}
