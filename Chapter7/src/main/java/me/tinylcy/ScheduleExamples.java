package me.tinylcy;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenyangli.
 */
public class ScheduleExamples {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    /**
     * Scheduling a task with a ScheduledExecutorService.
     */
    public static void schedule() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> future = executor.schedule(new Runnable() {
            public void run() {
                System.out.println("Now it is 10 seconds later.");
            }
        }, 10, TimeUnit.SECONDS);

        executor.shutdown();
    }

    /**
     * Scheduling a task with EventLoop.
     */
    public static void scheduleViaEventLoop() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            public void run() {
                System.out.println("Now it is 10 seconds later.");
            }
        }, 10, TimeUnit.SECONDS);
    }

    /**
     * Scheduling a recurring task with EventLoop.
     */
    public static void scheduleFixedViaEventLoop() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ScheduledFuture<?> future = channel.eventLoop().scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("Now it is 10 seconds later.");
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

}
