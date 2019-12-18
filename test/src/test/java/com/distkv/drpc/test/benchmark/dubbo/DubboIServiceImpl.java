package com.distkv.drpc.test.benchmark.dubbo;

import com.distkv.drpc.test.utils.MD5Utils;
import java.util.concurrent.CompletableFuture;

public class DubboIServiceImpl implements DubboIService {

  private static final int AVG_LAG = 10;

  @Override
  public CompletableFuture<DubboBenchmarkProtocol.Response> get(
      DubboBenchmarkProtocol.Request request) {
    CompletableFuture<DubboBenchmarkProtocol.Response> future = new CompletableFuture<>();
    DubboBenchmarkProtocol.Response response = DubboBenchmarkProtocol.Response.builder()
        .value(MD5Utils.MD5(request.getValue()))
        .build();
    future.complete(response);

    return future;
  }


}
