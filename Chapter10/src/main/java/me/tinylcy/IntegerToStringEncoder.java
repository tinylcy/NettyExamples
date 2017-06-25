package me.tinylcy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by chenyangli.
 * <p>
 * Class IntegerToStringEncoder
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list)
            throws Exception {
        list.add(String.valueOf(integer));
    }
}
