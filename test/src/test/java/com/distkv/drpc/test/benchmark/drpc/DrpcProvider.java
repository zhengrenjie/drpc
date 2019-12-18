package com.distkv.drpc.test.benchmark.drpc;

import com.distkv.drpc.DrpcServer;
import com.distkv.drpc.config.ServerConfig;
import com.distkv.drpc.test.benchmark.common.BenchmarkIService;
import com.distkv.drpc.test.benchmark.common.BenchmarkIServiceImpl;

public class DrpcProvider {

  public static void main(String[] args) {
    ServerConfig serverConfig = ServerConfig.builder()
        .workerThreadNum(256)
        .port(25500)
        .build();

    DrpcServer drpcServer = new DrpcServer(serverConfig);
    drpcServer.registerService(BenchmarkIService.class, new BenchmarkIServiceImpl());
    drpcServer.run();
  }

}
