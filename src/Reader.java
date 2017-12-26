import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import static java.lang.Boolean.TRUE;

/**
 * Created by lirus on 8/9/17.
 */
public class Reader extends Thread {
    Socket x;
    DataInputStream recv;
    String recv_message;

    public Reader(Socket sock) {
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
		while((recv_message=recv.readLine())!=null)
		{
               		System.out.println(recv_message);
               		 System.out.println("im working");
		}
            } catch (Exception e) {

                System.out.println("hello");
                e.printStackTrace();

            }
        }
    }
}


