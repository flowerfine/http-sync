package cn.sliew.http.stream.akka.framework;

import java.util.List;

public interface RootTask<Context extends JobContext, Sub extends SubTask> extends Lifecycle<Context> {

    List<Sub> split(Context context);
}
