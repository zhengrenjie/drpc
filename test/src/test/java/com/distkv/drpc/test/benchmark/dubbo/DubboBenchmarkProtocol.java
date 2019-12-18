package com.distkv.drpc.test.benchmark.dubbo;

import java.io.Serializable;
import lombok.Builder;

public class DubboBenchmarkProtocol {

  @Builder
  public static class Request implements Serializable {

    private static final long serialVersionUID = 5027830810124339452L;
    private String value;

    public String getValue() {
      return value;
    }
  }

  @Builder
  public static class Response implements Serializable {

    private static final long serialVersionUID = -4892659417113343338L;
    private String value;

    public String getValue() {
      return value;
    }
  }

}
