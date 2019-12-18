package com.distkv.drpc.test.benchmark.dubbo;

import java.util.concurrent.CompletableFuture;

public interface DubboIService {

  CompletableFuture<DubboBenchmarkProtocol.Response> get(DubboBenchmarkProtocol.Request request);

}
