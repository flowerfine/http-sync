package cn.sliew.http.stream.akka.util;

import akka.japi.Pair;
import cn.sliew.http.stream.common.util.DateUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public enum SyncOffsetUtil {
    ;

    public static List<Pair<Date, Date>> trySplit(Date startTime, Date endTime, List<Duration> gradients, int total) {
        Optional<Duration> optional = gradients.stream()
                .filter(gradient -> supportSplit(startTime, endTime, gradient))
                .findFirst();

        if (optional.isPresent() == false) {
            return Collections.emptyList();
        }

        return split(startTime, endTime, optional.get(), total);
    }

    private static boolean supportSplit(Date startTime, Date endTime, Duration gradient) {
        LocalDateTime start = DateUtil.toLocalDateTime(startTime);
        LocalDateTime end = DateUtil.toLocalDateTime(endTime);
        LocalDateTime nextStart = start.plus(gradient);
        return nextStart.isBefore(end);
    }

    private static List<Pair<Date, Date>> split(Date startTime, Date endTime, Duration gradient, int total) {
        LocalDateTime start = DateUtil.toLocalDateTime(startTime);
        LocalDateTime end = DateUtil.toLocalDateTime(endTime);

        List<Pair<Date, Date>> pairs = new LinkedList<>();
        LocalDateTime nextStart = start.plus(gradient);
        for (int i = 0; i < total; i++) {
            if (nextStart.isAfter(end)) {
                break;
            }
            pairs.add(new Pair<>(DateUtil.toDate(start), DateUtil.toDate(nextStart)));
            start = nextStart;
            nextStart = start.plus(gradient);
        }
        return pairs;
    }
}
