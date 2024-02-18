package cn.sliew.http.stream.akka.util;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public enum GradientUtil {
    ;

    public static List<Duration> getSparseGradients() {
        return Arrays.asList(
                Duration.ofDays(1L),
                Duration.ofHours(12L),
                Duration.ofHours(6L),
                Duration.ofHours(3L),
                Duration.ofHours(1L),
                Duration.ofMinutes(30L)
        );
    }

    public static List<Duration> getMediumGradients() {
        return Arrays.asList(
                Duration.ofHours(12L),
                Duration.ofHours(6L),
                Duration.ofHours(3L),
                Duration.ofHours(1L)
        );
    }

    public static List<Duration> getSmallGradients() {
        return Arrays.asList(
                Duration.ofHours(1L),
                Duration.ofMinutes(30L)
        );
    }

    public static List<Duration> getDefaultGradients() {
        return Arrays.asList(
                Duration.ofMinutes(15L),
                Duration.ofMinutes(5L),
                Duration.ofMinutes(2L),
                Duration.ofMinutes(1L));
    }

    public static List<Duration> getDenseGradients() {
        return Arrays.asList(
                Duration.ofMinutes(1L)
        );
    }
}
