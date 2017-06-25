package me.tinylcy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

/**
 * Created by chenyangli.
 * <p>
 * Graceful shutdown.
 */
public class GracefulShutdown {

    public void bootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("Channel Active.");
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
                            throws Exception {
                        System.out.println("Received data.");
                    }
                });
        bootstrap.connect(new InetSocketAddress("tinylcy.me", 80)).syncUninterruptibly();
        Future<?> future = group.shutdownGracefully();
        future.syncUninterruptibly();
    }

    public static void main(String[] args) {
        new GracefulShutdown().bootstrap();
    }
}
