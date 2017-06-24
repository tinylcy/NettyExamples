package me.tinylcy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;


/**
 * Created by chenyangli.
 * <p>
 * Asynchronous connect.
 * <p>
 * Callback in action.
 */
public class ConnectExample {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void connect() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        final ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 8080));
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    ChannelFuture wf = channelFuture.channel().writeAndFlush(buffer);
                } else {
                    Throwable cause = channelFuture.cause();
                    cause.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new ConnectExample().connect();
    }

}
