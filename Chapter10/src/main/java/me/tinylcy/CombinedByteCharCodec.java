package me.tinylcy;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Created by chenyangli.
 * <p>
 * CombinedChannelDuplexHandler
 */
public class CombinedByteCharCodec
        extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
