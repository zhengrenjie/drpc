package com.distkv.drpc.test.benchmark;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

  private static final int AVG_LAG = 30;

  public static void main(String[] args) throws Exception {

    DefaultEventExecutorGroup executors = new DefaultEventExecutorGroup(128);
//    ExecutorService executors = Executors.newFixedThreadPool(128);
    long b = System.currentTimeMillis();
    List<Future> futureList = new LinkedList<>();
    for(int i = 0;i<1000;i++) {
      Future future = executors.submit(() -> {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        try {
          TimeUnit.MILLISECONDS.sleep(nextRTT(random));
        }catch (Exception e) {
          e.printStackTrace();
        }
      });
      futureList.add(future);
    }
    for (Future future : futureList) {
      future.get();
    }
    System.out.println(System.currentTimeMillis() - b);

  }

  private static long nextRTT(ThreadLocalRandom random) {
    double u = random.nextDouble();
    int x = 0;
    double cdf = 0;
    while (u >= cdf) {
      x++;
      cdf = 1 - Math.exp(-1.0D * 1 / AVG_LAG * x);
    }
    return x;
  }

}
