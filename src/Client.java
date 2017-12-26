/**
 * Created by lirus on 8/9/17.
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Boolean.TRUE;

public class Client{
    public static Socket sock;

    public static void main(String[] args)
    {
        try{
            sock = new Socket("127.0.0.1", 8888);
            Reader reader=new Reader(sock);
            reader.start();
            Writer writer=new Writer(sock);
            writer.start();
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
        catch(Exception e)
        {
		System.out.println("infinite client");
            e.printStackTrace();
        }
    }
}

