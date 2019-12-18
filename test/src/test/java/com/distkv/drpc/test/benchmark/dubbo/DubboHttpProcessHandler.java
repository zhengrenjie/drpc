package com.distkv.drpc.test.benchmark.dubbo;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.INTERNAL_SERVER_ERROR;

import com.distkv.drpc.test.benchmark.common.Constants;
import com.distkv.drpc.test.utils.MD5Utils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.util.CharsetUtil;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class DubboHttpProcessHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DubboHttpProcessHandler.class);
  private DubboIService service;
  private ThreadLocalRandom random = ThreadLocalRandom.current();
  public DubboHttpProcessHandler(DubboIService service) {
    this.service = service;
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
    long start = System.currentTimeMillis();
    String value = Constants.TEST_CONTENT + UUID.randomUUID();

    // put
    DubboBenchmarkProtocol.Request request = DubboBenchmarkProtocol.Request.builder()
        .value(value)
        .build();

    // get
    service.get(request)
        .whenComplete((actual, t) -> {
          if (t == null && MD5Utils.MD5(value).equals(actual.getValue())) {
            FullHttpResponse ok =
                new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled
                    .copiedBuffer("OK\n", CharsetUtil.UTF_8));
            ok.headers().add(HttpHeaderNames.CONTENT_LENGTH, 3);
            ctx.writeAndFlush(ok);
            if (LOGGER.isInfoEnabled()) {
              LOGGER.info("Request result:success cost:{} ms", System.currentTimeMillis() - start);
            }
          } else {
            FullHttpResponse error =
                new DefaultFullHttpResponse(HTTP_1_1, INTERNAL_SERVER_ERROR);
            error.headers().add(HttpHeaderNames.CONTENT_LENGTH, 0);
            ctx.writeAndFlush(error);
            LOGGER.info("Request result:failure cost:{} ms", System.currentTimeMillis() - start, t);
          }
        });
  }

}
