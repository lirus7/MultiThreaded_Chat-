import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Boolean.TRUE;

/**
 * Created by lirus on 9/9/17.
 */
public class Writer_2015104 extends Thread{
    Socket x;
    DataOutputStream send;
    String send_message;

    public Writer_2015104(Socket sock) {
        x = sock;
        try {
            send= new DataOutputStream(x.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run()
    {
        Scanner scanner=new Scanner(System.in);
        while(TRUE)
        {
            try {
                send_message = scanner.nextLine();
                send.writeUTF(send_message);
                send.flush();
                if (send_message.equals("EXIT"))
                    System.exit(0);
              //  System.out.println("current thread");
                /*
                recv_message=recv.readUTF().toString();
                System.out.println(recv_message);*/
            }
            catch(Exception e)
            {e.printStackTrace();}
         }
    }

}
