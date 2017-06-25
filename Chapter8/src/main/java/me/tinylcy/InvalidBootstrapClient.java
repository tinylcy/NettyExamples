package me.tinylcy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by chenyangli.
 * <p>
 * Incompatible Channel and EventloopGroup.
 */
public class InvalidBootstrapClient {

    public void bootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(OioSocketChannel.class).
                handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
                            throws Exception {
                        System.out.println("Received data.");
                    }
                });

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("tinylcy.me", 80));
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Connection established.");
                } else {
                    System.err.println("Connection attempt failed.");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new InvalidBootstrapClient().bootstrap();
    }

}
