package com.hf.job04.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author hfzhang
 * @date 2021/1/18
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try {
            //channelRead流量接口开始请求
            System.out.println("channelRead流量接口请求开始，时间为："+new Date());
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
            String uri = fullHttpRequest.uri();
            System.out.println("接收到的请求uri为："+uri);
            if(uri.contains("/test")){
                handlerTest(fullHttpRequest, ctx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        FullHttpResponse fullHttpResponse = null;
        try {
            String value = "hello hfzhang!";
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(value.getBytes("utf-8")));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
        } catch (Exception e) {
            System.out.println("处理出错："+e.getMessage());
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1,NO_CONTENT);
        } finally {
            if(fullHttpRequest != null){
                if(!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                }else{
                    fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
