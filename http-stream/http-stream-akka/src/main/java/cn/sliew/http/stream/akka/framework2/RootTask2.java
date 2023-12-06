package cn.sliew.http.stream.akka.framework2;

import java.util.List;

public interface RootTask2<Context extends JobContext2, Sub extends SubTask2> {

    List<Sub> split(Context context);
}
