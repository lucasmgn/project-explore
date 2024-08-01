package br.com.explore.explore.model.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public class ExchangeRateResponse {

    private String result;

    @JsonProperty("terms_of_use")
    private String termsOfUse;

    @JsonProperty("time_last_update_unix")
    private Long timeLastUpdateUnix;

    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    private Long timeNextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    private String timeNextUpdateUtc;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty(value = "conversion_rates", required = true)
    private Map<String, BigDecimal> conversionRates;
}
