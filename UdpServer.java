// Write a program on datagram socket for client/server to display the messages on client side, typed at the server side. 

import java.net.*;

public class UdpServer {
    public static void main(String[] args) {
        DatagramSocket skt = null;

        try {
            skt = new DatagramSocket(6788);

            byte[] buffer = new byte[1000];

            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            skt.receive(request);

            String[] message = (new String(request.getData())).split(" ");
            byte[] sendMsg = ("This is Network Laboratory").getBytes();

            DatagramPacket reply = new DatagramPacket(sendMsg, sendMsg.length, request.getAddress(), request.getPort());
            skt.send(reply);

            System.out.println("Client communicated");

            skt.close();
        } catch(Exception ex) {}
    }
}




import java.net.*;

public class UdpClient {
    public static void main(String[] args) {
        DatagramSocket skt;

        try {
            skt = new DatagramSocket();
            String msg = "udp";

            InetAddress host = InetAddress.getByName("127.0.0.1");
            int port = 6788;

            byte[] b = msg.getBytes();
            DatagramPacket request = new DatagramPacket(b, b.length, host, port);
            skt.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            skt.receive(reply);

            System.out.println("Client received: " + new String(reply.getData()));

            skt.close();
        } catch(Exception ex) {}
    }
}
