import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by lirus on 30/8/17.
 */
public class Server {
    static int count=0;
    static ArrayList<Serv_Client_2015104>client_sockets=new ArrayList<Serv_Client_2015104>();
    public static void main(String[] args)
    {
        try{
            ServerSocket socket=new ServerSocket(8888);
            System.out.println("This is the Multithreaded_Server_Client program");
        
        Socket client=socket.accept();
        count++;
        System.out.println("Client "+count+ "  has joined the chat");
        Serv_Client_2015104 new_client =new Serv_Client_2015104(client,count);
        client_sockets.add(new_client);
        new_client.start();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
