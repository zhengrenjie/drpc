package com.distkv.drpc.test.benchmark.brpc;

import com.distkv.drpc.test.benchmark.dubbo.DubboBenchmarkProtocol;
import com.distkv.drpc.test.generated.BenchmarkProtocol;
import java.util.concurrent.CompletableFuture;

public interface BrpcIService {

  BenchmarkProtocol.Response get(BenchmarkProtocol.Request request);

}
