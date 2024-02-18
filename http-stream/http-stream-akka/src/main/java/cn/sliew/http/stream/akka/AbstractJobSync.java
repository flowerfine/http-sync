package cn.sliew.http.stream.akka;

import akka.Done;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.SpawnProtocol;
import akka.japi.Pair;
import akka.stream.UniqueKillSwitch;
import cn.sliew.milky.common.exception.Rethrower;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractJobSync extends AbstractJob {

    public AbstractJobSync(MeterRegistry meterRegistry, ActorSystem<SpawnProtocol.Command> actorSystem) {
        super(meterRegistry, actorSystem);
    }

    @Override
    void handleExecuteAsyncResult(Object param, Pair<UniqueKillSwitch, CompletionStage<Done>> pair) {
        UniqueKillSwitch killSwitch = pair.first();
        try {
            pair.second().toCompletableFuture().get(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            killSwitch.abort(e);
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            killSwitch.abort(e);
        } catch (Exception e) {
            killSwitch.abort(e);
            Rethrower.throwAs(e);
        }
    }
}
