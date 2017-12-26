/**
 * Created by lirus on 8/9/17.
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Boolean.TRUE;

public class Client_2015104{

    public static void main(String[] args)
    {
        try {
            Socket sock = new Socket("127.0.0.1", 8888);
            Reader_2015104 reader = new Reader_2015104(sock);
            Writer_2015104 writer = new Writer_2015104(sock);
            reader.start();
            writer.start();
        }
        catch(Exception e){e.printStackTrace();}
        /*
            while(!send_message.equals("Exit"))
            {
                send_message=scanner.nextLine();
                send.println(send_message);
                send.flush();
                System.out.println("current thread");
                /*
                recv_message=recv.readUTF().toString();
                System.out.println(recv_message);

            }
            */

        }

}

