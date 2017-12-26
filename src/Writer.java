import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Boolean.TRUE;

/**
 * Created by lirus on 9/9/17.
 */
public class Writer extends Thread{
    Socket x;
    PrintStream send;
    String send_message;

    public Writer(Socket sock) {
        x = sock;
        try {
            send= new PrintStream(x.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run()
    {
        Scanner scanner=new Scanner(System.in);
        while(TRUE)
        {
	    while(send_message==null) {
          	  send_message=scanner.nextLine();
		}
            send.println(send_message);
            send.flush();
            if(send_message.equals("Exit"))
                System.exit(0);
            System.out.println("current thread");
                /*
                recv_message=recv.readUTF().toString();
                System.out.println(recv_message);*/
         }
    }

}
