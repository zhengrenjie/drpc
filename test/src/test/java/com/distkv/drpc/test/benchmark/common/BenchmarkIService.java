package com.distkv.drpc.test.benchmark.common;

import com.distkv.drpc.test.generated.BenchmarkProtocol;
import com.distkv.drpc.test.generated.EchoProtocol;
import java.util.concurrent.CompletableFuture;

public interface BenchmarkIService {

  CompletableFuture<BenchmarkProtocol.Response> service(BenchmarkProtocol.Request request);

}
