package NIODemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author - ZwZ
 * @date - 2020/2/17 - 19:11
 * @Description:服务器端 BIO
 */
public class SocketServer {
    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8089));
            while (true) {
                System.out.println("wait con");
                Socket socket = serverSocket.accept();//8080端口没有请求时一直阻塞
                System.out.println("con success");
                System.out.println("wait data");
                /*读取发送端的数据 如果客户端一直不发，read()会阻塞 read表示读取的字节数
                 * 所以，为了适应并发的环境，要把read()放入一个线程的run()内
                 * 这样子就不会因为客户端一直不发数据而导致这个主程序无法向下执行去处理其他的连接
                 * 每来一个连接就会开一个线程，这样子会浪费资源
                 * 所以这就是BIO的弊端
                 */
                int read = socket.getInputStream().read(bytes);
                System.out.println("data success");
                String content = new String(bytes);
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
