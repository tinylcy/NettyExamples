package me.tinylcy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.*;

import java.util.List;

/**
 * Created by chenyangli.
 * <p>
 * Using MessageToMessageCodec
 */
public class WebSocketConverterHandler extends MessageToMessageCodec<WebSocketFrame, MyWebSocketFrame> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyWebSocketFrame myWebSocketFrame,
                          List<Object> list) throws Exception {
        ByteBuf payload = myWebSocketFrame.getData().duplicate().retain();
        switch (myWebSocketFrame.getType()) {
            case BINARY:
                list.add(new BinaryWebSocketFrame(payload));
                break;
            case TEXT:
                list.add(new TextWebSocketFrame(payload));
                break;
            case CLOSE:
                list.add(new CloseWebSocketFrame(true, 0, payload));
                break;
            case CONTINUATION:
                list.add(new ContinuationWebSocketFrame(payload));
                break;
            case PONG:
                list.add(new PongWebSocketFrame(payload));
                break;
            case PING:
                list.add(new PingWebSocketFrame(payload));
                break;
            default:
                throw new IllegalArgumentException(
                        "Unsupported websocket msg: " + myWebSocketFrame);
        }
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame,
                          List<Object> list) throws Exception {
        ByteBuf payload = webSocketFrame.content().duplicate().retain();
        if (webSocketFrame instanceof BinaryWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CLOSE, payload));
        } else if (webSocketFrame instanceof CloseWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CLOSE, payload));
        } else if (webSocketFrame instanceof PingWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PING, payload));
        } else if (webSocketFrame instanceof PongWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PING, payload));
        } else if (webSocketFrame instanceof TextWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.TEXT, payload));
        } else if (webSocketFrame instanceof ContinuationWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CONTINUATION, payload));
        } else {
            throw new IllegalStateException("Unsupported websocket msg: " + webSocketFrame);
        }
    }
}
