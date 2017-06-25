package me.tinylcy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by chenyangli.
 * <p>
 * Class CharToMessageEncoder
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Character character,
                          ByteBuf byteBuf) throws Exception {
        byteBuf.writeChar(character);
    }
}
