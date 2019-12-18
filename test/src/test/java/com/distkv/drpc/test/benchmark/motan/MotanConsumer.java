package com.distkv.drpc.test.benchmark.motan;

import com.distkv.drpc.test.benchmark.dubbo.DubboBenchmarkGateway;
import com.distkv.drpc.test.benchmark.dubbo.DubboIService;
import com.distkv.drpc.test.benchmark.dubbo.DubboIServiceImpl;
import com.weibo.api.motan.config.ProtocolConfig;
import com.weibo.api.motan.config.RefererConfig;
import com.weibo.api.motan.config.RegistryConfig;

public class MotanConsumer {


  public static void main(String[] args) {
    RefererConfig<MotanIService> motanDemoServiceReferer = new RefererConfig<>();

    motanDemoServiceReferer.setInterface(MotanIService.class);

    motanDemoServiceReferer.setGroup("motan-demo-rpc");
    motanDemoServiceReferer.setVersion("1.0");
    motanDemoServiceReferer.setRequestTimeout(3000);

    RegistryConfig registry = new RegistryConfig();
    registry.setRegProtocol("direct");
    registry.setAddress("127.0.0.1:25500");
    motanDemoServiceReferer.setRegistry(registry);

    ProtocolConfig protocol = new ProtocolConfig();
    protocol.setId("motan");
    protocol.setName("motan");
    motanDemoServiceReferer.setProtocol(protocol);

    MotanIService service = motanDemoServiceReferer.getRef();
    MotanBenchmarkGateway gateway = new MotanBenchmarkGateway();
    gateway.start(service);

  }
}
