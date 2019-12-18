package com.distkv.drpc.test.benchmark.brpc;

import com.distkv.drpc.test.benchmark.dubbo.DubboBenchmarkProtocol;
import com.distkv.drpc.test.generated.BenchmarkProtocol;
import com.distkv.drpc.test.utils.MD5Utils;

public class BrpcIServiceImpl implements BrpcIService {

  @Override
  public BenchmarkProtocol.Response get(
      BenchmarkProtocol.Request request) {
    BenchmarkProtocol.Response response = BenchmarkProtocol.Response.newBuilder()
        .setValue(MD5Utils.MD5(request.getValue()))
        .build();

    return response;
  }


}
