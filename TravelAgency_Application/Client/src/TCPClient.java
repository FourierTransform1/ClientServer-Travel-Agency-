import java.io.*;
import java.net.Socket;
import java.nio.CharBuffer;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TCPClient {


    private Socket socket;


    public TCPClient() throws IOException {
        this.socket = new Socket("localhost", 2345);
    }


    public void communicate() {

        try {


            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            Long l;

            String message;


            while (true) {


                if (inputFromServer.ready()) {

                    do {
                        message = inputFromServer.readLine().trim();
                        System.out.println(message);

                        if (message.equals("/FILE/")) {

                            fileReceiver(inputFromServer);
                            System.out.println("disconnecting..");
                            socket.close();
                            System.exit(0);
                        }


                    } while ((!message.equals("")) && (inputFromServer.ready()));


                } else {
                    continue;
                }

                String in = scanner.nextLine();

                printWriter.println(in);

                printWriter.flush();


            }


        } catch (IOException ex) {

            System.out.println("Could not find server");

        }
    }


    private void fileCopier(File source, File dest) {
        int n, i;

        long t;

        try {

            FileInputStream in = new FileInputStream(source);

            FileOutputStream out = new FileOutputStream(dest);

            i = 0;

            t = System.currentTimeMillis();

            while ((n = in.read()) != -1) {
                i++;
                out.write(n);
            }

            t = System.currentTimeMillis() - t;

            in.close();

            out.close();

            System.out.println("File copy from " + source + " to" + dest + " is complete (" + i + " bytes.) in " + t + " milli sec.");

        } catch (FileNotFoundException fnfe) {

            System.err.println("File is not found");

        } catch (IOException ioe) {

            ioe.printStackTrace();

        }


    }


    public File download(File src) {
        File dst = new File("/Users/ahmedmohamud/Desktop/Receipts/receipt.txt");
        fileCopier(src, dst);
        return dst;
    }


    public void fileReceiver(BufferedReader bufferedReader) throws IOException {


        try {


            char[] arr = new char[1024];

            bufferedReader.read(arr, 0, arr.length);

            File file = new File("/Users/ahmedmohamud/Desktop/Receipts/receipt.txt");

            PrintWriter printWriter = new PrintWriter(new FileOutputStream(file), true);
            //FileOutputStream out = new FileOutputStream(file);


            int i = 0;


            while (!(arr[i] == '\0')) {
                printWriter.print(arr[i]);
                //out.write(arr[i]);
                i++;
            }


            System.out.println("File " + file.getName()
                    + " downloaded (" + file.length() + " bytes read)");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }


}
