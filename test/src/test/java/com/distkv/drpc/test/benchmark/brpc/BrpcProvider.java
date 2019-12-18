package com.distkv.drpc.test.benchmark.brpc;

import com.baidu.brpc.server.RpcServer;
import com.baidu.brpc.server.RpcServerOptions;
import com.distkv.drpc.test.benchmark.dubbo.DubboIServiceImpl;

public class BrpcProvider {

  public static void main(String[] args) {
    int port = 25500;
    if (args.length == 1) {
      port = Integer.valueOf(args[0]);
    }

    RpcServerOptions options = new RpcServerOptions();
    options.setReceiveBufferSize(64 * 1024 * 1024);
    options.setSendBufferSize(64 * 1024 * 1024);
    options.setWorkThreadNum(256);
    final RpcServer rpcServer = new RpcServer(port, options);
    rpcServer.registerService(new BrpcIServiceImpl());
    rpcServer.start();

    // make server keep running
    synchronized (BrpcConsumer.class) {
      try {
        BrpcConsumer.class.wait();
      } catch (Throwable e) {
      }
    }
  }
}
