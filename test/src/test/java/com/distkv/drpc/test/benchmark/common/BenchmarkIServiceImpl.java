package com.distkv.drpc.test.benchmark.common;

import com.distkv.drpc.test.generated.BenchmarkProtocol;
import com.distkv.drpc.test.utils.MD5Utils;
import java.util.concurrent.CompletableFuture;

public class BenchmarkIServiceImpl implements BenchmarkIService {

  @Override
  public CompletableFuture<BenchmarkProtocol.Response> service(
      BenchmarkProtocol.Request request) {
    CompletableFuture<BenchmarkProtocol.Response> future = new CompletableFuture<>();
    BenchmarkProtocol.Response response = BenchmarkProtocol.Response.newBuilder()
        .setValue(MD5Utils.MD5(request.getValue()))
        .build();
    future.complete(response);

    return future;
  }

}