package com.distkv.drpc.test.benchmark.brpc;

import com.baidu.brpc.RpcContext;
import com.baidu.brpc.client.BrpcProxy;
import com.baidu.brpc.client.RpcCallback;
import com.baidu.brpc.client.RpcClient;
import com.baidu.brpc.client.RpcClientOptions;
import com.baidu.brpc.client.loadbalance.LoadBalanceStrategy;
import com.baidu.brpc.interceptor.Interceptor;
import com.baidu.brpc.protocol.Options;
import com.baidu.brpc.server.RpcServer;
import com.baidu.brpc.server.RpcServerOptions;
import com.distkv.drpc.test.benchmark.dubbo.DubboBenchmarkGateway;
import com.distkv.drpc.test.benchmark.dubbo.DubboIService;
import com.distkv.drpc.test.benchmark.dubbo.DubboIServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class BrpcConsumer {

  public static void main(String[] args) {
    RpcClientOptions clientOption = new RpcClientOptions();
    clientOption.setProtocolType(Options.ProtocolType.PROTOCOL_BAIDU_STD_VALUE);
    clientOption.setWriteTimeoutMillis(3000);
    clientOption.setReadTimeoutMillis(5000);
    clientOption.setMaxTotalConnections(1000);
    clientOption.setMinIdleConnections(10);

    String serviceUrl = "list://127.0.0.1:25500";

    List<Interceptor> interceptors = new ArrayList<>();

    // sync call
    RpcClient rpcClient = new RpcClient(serviceUrl, clientOption, interceptors);
    BrpcIService iService = BrpcProxy.getProxy(rpcClient, BrpcIService.class);
    RpcContext.getContext().setLogId(1234);

    BrpcBenchmarkGateway benchmarkGateway = new BrpcBenchmarkGateway();
    benchmarkGateway.start(iService);

    rpcClient.stop();

  }

}
