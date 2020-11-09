import java.io.IOException;
import java.net.SocketException;

public class Main {


    public static void main(String[] args) {

        try{

            TCPClient client = new TCPClient();

            client.communicate();


        } catch (SocketException socEx){

            System.out.println(socEx);

        } catch (IOException  ioe){

            System.out.println(ioe);
        }

    }

}











