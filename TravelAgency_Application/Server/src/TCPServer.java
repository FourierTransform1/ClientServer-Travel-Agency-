import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class TCPServer {

    public static void main(String[] args) {

        try {

            ServerSocket server = new ServerSocket(2345);

            while (true) {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
            }
        }catch (Exception e) {
            System.err.println("Exception caught:" + e);

        }
    }






}






