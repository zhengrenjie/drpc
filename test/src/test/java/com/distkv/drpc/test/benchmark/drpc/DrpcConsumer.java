package com.distkv.drpc.test.benchmark.drpc;

import com.distkv.drpc.Proxy;
import com.distkv.drpc.api.Client;
import com.distkv.drpc.config.ClientConfig;
import com.distkv.drpc.netty.NettyClient;
import com.distkv.drpc.test.benchmark.common.BenchmarkIService;

public class DrpcConsumer {

  public static void main(String[] args) {
    ClientConfig clientConfig = ClientConfig.builder()
        .address("127.0.0.1:25500")
        .build();
    Client client = new NettyClient(clientConfig);
    client.open();

    Proxy<BenchmarkIService> proxy = new Proxy<>();
    proxy.setInterfaceClass(BenchmarkIService.class);
    BenchmarkIService service = proxy.getService(client);

    // benchmark gateway
    BenchmarkGateway gateway = new BenchmarkGateway();
    gateway.start(service);
  }
}
