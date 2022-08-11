package designPattern.proxy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Demo9 implements Cloneable {
    public static void main(String[] args) throws Exception {
        //获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9901));
        //切换为非阻塞模式
        channel.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //发送数据给服务端
        Scanner sc = new Scanner(System.in);
    }
}

