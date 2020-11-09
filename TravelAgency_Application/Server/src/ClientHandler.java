import javafx.util.converter.ByteStringConverter;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Stream;

public class ClientHandler extends Thread {

    private Socket client;
    private TCPServer server;
    private int noOfTravellers;


    ClientHandler(Socket client) {
        this.client = client;
    }


    public void run() {

        try {

            //PrintWriter writer;
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            server = new TCPServer();

            RegistrationHandler registrator = new RegistrationHandler();

            String message;
            String reply;


            int n = 0;


            while (true) {


                if(n==0) {

                    String menu = registrator.menu();
                    writer.println(menu);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setNumTravellers(Integer.valueOf(reply));
                    n+=1;
                }


                if(n==1) {
                    message = "[2] Ferry or Air travel?";
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setModeOfTraver(reply);
                    n+=1;
                }


                if(n==2) {
                    message = "[3] Number of days?";
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setNumDays(Integer.valueOf(reply));
                    n+=1;
                }


                if(n==3) {
                    message = "[4] Meals included? (yes or no)";
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setMeals(reply);
                    n+=1;
                }


                if(n==4) {
                    message = "[5] Contact name: ";
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setName(reply);
                    n+=1;
                }


                if(n==5) {
                    message = "[6] Phonenumber: ";
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setPhoneNumber(reply);
                    n+=1;
                }


                if(n==6) {
                    message = "[7] Address: ";
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();
                    registrator.setAddress(reply);
                    n+=1;
                }


                if(n==7) {
                    message = "Total  price = " + registrator.priceCalculator() + " Would you like to book?";
                    System.out.println(message);
                    writer.println(message);
                    writer.flush();
                    reply = reader.readLine();

                    if(reply.equalsIgnoreCase("yes")){
                        System.out.println("BOOK ORDER!");
                        sendReceiptFile(registrator.printReceipt());
                    }
                    n+=1;
                }





            }

        } catch (Exception e) {
            System.err.println("Exception caught: client disconnected.");
            System.out.println(e);

        } finally {

            try {

                client.close();

            } catch (Exception e) {
                System.out.println(e);
            }


        }
    }


    public void sendReceiptFile(File file) throws IOException {

        BufferedInputStream bis = null;
        OutputStream os = null;
        PrintWriter pw = null;


        try{

            byte [] mybytearray  = new byte [(int)file.length()];

            bis = new BufferedInputStream(new FileInputStream(file));

            bis.read(mybytearray,0,mybytearray.length);

            System.out.println("Sending " + file.getName() + "(" + mybytearray.length + " bytes)");


            os = client.getOutputStream();

            pw = new PrintWriter(os, true);

            pw.println("/FILE/");

            pw.flush();

            os.write(mybytearray,0,mybytearray.length);

            os.flush();

            System.out.println("Done sending file.");




        }catch (IOException ioe){
            ioe.printStackTrace();

        }finally {

            os.close();
            bis.close();

        }





    }



}
