package br.com.exchange_project.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransformUnixUtils {

    public static Duration convertUnixForDuration(Long timeNextUpdateUnix) {
        var now = Instant.now();
        var updateTime = Instant.ofEpochSecond(timeNextUpdateUnix);
        return Duration.between(now, updateTime);
    }
}
