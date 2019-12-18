package com.distkv.drpc.test.benchmark.dubbo;

import java.util.HashMap;
import java.util.Map;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.remoting.Constants;

public class DubboConsumer {

  public static void main(String[] args) {
    ApplicationConfig application = new ApplicationConfig();
    application.setName("dubbo-benchmark");

    RegistryConfig registry = new RegistryConfig();
    registry.setAddress("N/A");

    ReferenceConfig<DubboIService> reference = new ReferenceConfig<>();
    reference.setApplication(application);
    reference.setUrl("dubbo://localhost:25500");
    reference.setInterface(DubboIService.class);
    Map<String, String> attributes = new HashMap<>();
    attributes.put("async", "true");
    attributes.put(Constants.HEARTBEAT_KEY, "0");
    attributes.put(Constants.RECONNECT_KEY, "false");
    reference.setParameters(attributes);
    DubboIService service = reference.get();

    // benchmark gateway
    DubboBenchmarkGateway server = new DubboBenchmarkGateway();
    server.start(service);
  }
}