package me.tinylcy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by chenyangli.
 */
public class ByteBufSliceAndCopy {

    /**
     * 使用slice避免内存的复制开销.
     */
    public static void slice() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 15);
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) == sliced.getByte(0);
        System.out.println(buf.toString(utf8));
        System.out.println(sliced.toString(utf8));
    }

    public static void copy() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 15);
        buf.setByte(0, (byte) 'J');
        assert buf.getByte(0) != copy.getByte(0);
        System.out.println(buf.toString(utf8));
        System.out.println(copy.toString(utf8));
    }

    public static void main(String[] args) {
        slice();
        copy();
    }
}
