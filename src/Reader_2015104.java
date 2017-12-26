import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import static java.lang.Boolean.TRUE;

/**
 * Created by lirus on 8/9/17.
 */
public class Reader_2015104 extends Thread {
    Socket x;
    DataInputStream recv;
    String recv_message;

    public Reader_2015104(Socket sock) {
        x = sock;
        try {
            recv = new DataInputStream(x.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (TRUE) {
            try {
                    while((recv_message=recv.readUTF().toString())==null){;}
               		System.out.println(recv_message);
               	//	 System.out.println("im working");
		        }
             catch (Exception e) {

                System.out.println("Error");
                e.printStackTrace();

            }
        }
    }
}


