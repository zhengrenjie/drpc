package com.distkv.drpc.test.benchmark.motan;

import com.distkv.drpc.test.benchmark.dubbo.DubboIService;
import com.distkv.drpc.test.benchmark.dubbo.DubboIServiceImpl;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.config.ProtocolConfig;
import com.weibo.api.motan.config.RegistryConfig;
import com.weibo.api.motan.config.ServiceConfig;
import com.weibo.api.motan.util.MotanSwitcherUtil;

public class MotanProvider {

  public static void main(String[] args) {
    ServiceConfig<MotanIService> motanDemoService = new ServiceConfig<>();

    motanDemoService.setInterface(MotanIService.class);
    motanDemoService.setRef(new MotanIServiceImpl());

    motanDemoService.setGroup("motan-demo-rpc");
    motanDemoService.setVersion("1.0");

    RegistryConfig registry = new RegistryConfig();
    registry.setRegProtocol("local");
    motanDemoService.setRegistry(registry);

    // 配置RPC协议
    ProtocolConfig protocol = new ProtocolConfig();
    protocol.setId("motan");
    protocol.setName("motan");
    motanDemoService.setProtocol(protocol);

    motanDemoService.setExport("motan:25500");
    motanDemoService.export();

    MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);

    System.out.println("server start...");
  }
}
