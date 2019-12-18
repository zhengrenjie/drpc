package com.distkv.drpc.test.benchmark.motan;

import com.distkv.drpc.test.benchmark.dubbo.DubboBenchmarkProtocol;
import com.distkv.drpc.test.utils.MD5Utils;

public class MotanIServiceImpl implements MotanIService {

  @Override
  public DubboBenchmarkProtocol.Response get(
      DubboBenchmarkProtocol.Request request) {
    DubboBenchmarkProtocol.Response response = DubboBenchmarkProtocol.Response.builder()
        .value(MD5Utils.MD5(request.getValue()))
        .build();

    return response;
  }


}
