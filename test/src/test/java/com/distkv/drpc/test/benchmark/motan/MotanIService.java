package com.distkv.drpc.test.benchmark.motan;

import com.distkv.drpc.test.benchmark.dubbo.DubboBenchmarkProtocol;
import java.util.concurrent.CompletableFuture;

public interface MotanIService {

  DubboBenchmarkProtocol.Response get(DubboBenchmarkProtocol.Request request);

}
