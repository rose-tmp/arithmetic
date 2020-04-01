package NIODemo;

import java.io.IOException;
import java.net.Socket;

/**
 * @author - ZwZ
 * @date - 2020/2/17 - 19:18
 * @Description:客户端 BIO
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8089);
            socket.getOutputStream().write("111".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
