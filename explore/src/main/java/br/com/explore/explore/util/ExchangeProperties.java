package br.com.explore.explore.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "exchange.api")
public class ExchangeProperties {

    private String url;
}
