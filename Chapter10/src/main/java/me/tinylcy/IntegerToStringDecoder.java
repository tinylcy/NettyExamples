package me.tinylcy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by chenyangli.
 * <p>
 * Class IntegerToStringDecoder
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list)
            throws Exception {
        list.add(String.valueOf(integer));
    }
}
