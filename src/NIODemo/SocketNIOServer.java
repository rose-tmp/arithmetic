package NIODemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/2/17 - 20:09
 * @Description:NIO形式的服务器端
 */
public class SocketNIOServer {
    static byte[] bytes = new byte[1024];
    static List<SocketChannel> list = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//ByteBuffer可以申请到堆外内存
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8089));
            serverSocketChannel.configureBlocking(false);//设置成非阻塞
            while(true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                if(socketChannel == null){
                    System.out.println("没有人连接");
                    //看之前连接过的客户端有没有发数据 这里只是模拟，真实情况下是交给linux内核方法epoll()或者windows的select()去完成这个轮询
                    for (SocketChannel client:list
                         ) {
                        client.read(byteBuffer);

                    }
                }else{
                    socketChannel.configureBlocking(false);//设置成非阻塞
                    list.add(socketChannel);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
