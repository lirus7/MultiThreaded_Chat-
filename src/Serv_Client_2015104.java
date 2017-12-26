import javax.xml.crypto.Data;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by lirus on 30/8/17.
 */
public class Serv_Client_2015104 extends Thread {

    int count;
    Socket me;
    DataInputStream sent;
    DataOutputStream recv;
    public Serv_Client_2015104(Socket sock,int count)
    {
        try {
            this.me = sock;
            this.count = count;
            sent = new DataInputStream(me.getInputStream());
            recv = new DataOutputStream(me.getOutputStream());
        }
        catch(Exception e){e.printStackTrace();}
    }
    public String reverseit(String s)
    {
        String[] sentence=s.split(" ");
        String ans="";
        for(int i=sentence.length-1;i>=0;i--)
            ans+=sentence[i]+" ";
        return ans;
    }
    public void run()
    {
        try{
            while(this.me.isConnected()){
                String sent_message="";
                String recv_message="";
                sent_message=sent.readUTF().toString();

                System.out.println("Client "+this.count +": "+sent_message);
                String[] words = sent_message.split("\\s+");
                for(int i=0;i<words.length;i++)
                    System.out.println(words[i]);

                if(words[0].equals("EXIT")) {
                    Server_2015104.client_sockets.set(this.count-1,null);
                    recv_message = "Client" + this.count + " has disconnected";
                    System.out.println(recv_message);
                }
                else if(words[0].equals("All"))//For handling all the clients
                {
                    String s="";
                    for (int i=1;i<words.length;i++)
                        s+=words[i];
                    System.out.println(s);
                    for (Serv_Client_2015104 c:Server_2015104.client_sockets)
                    {
                        if(c.count!=this.count) {
                            System.out.println(c.count);
                            c.recv.writeUTF(s);
                            c.recv.flush();
                        }
                    }

                    recv_message="The message sent to all the connected threads";
                    System.out.println(recv_message);
                }
                else if(words[0].equals("Client"))// personal message to a single and multiple ppl
                {
                    if(words[1].length()==1 && words[3].length()!=1)
                    {
                        int index=Integer.parseInt(words[1]);
                        int bool=0;
                        String s="";
                        for (int i=3;i<words.length;i++)
                            s+=words[i];
                        for (Serv_Client_2015104 c:Server_2015104.client_sockets)
                            if(c!=null && c.count==index)
                            {
                                c.recv.writeUTF(s);
                                c.recv.flush();
                                bool=1;
                            }
                        if(bool==0) {
                            recv_message = "This client does not exist";
                            recv.writeUTF(recv_message);

                        }

                    }
                    else if(words[3].length()==1 && words[1].length()==1)
                    {
                        int index=Integer.parseInt(words[1]);
                        int bool=0;
                        String s="";
                        for (int i=5;i<words.length;i++)
                            s+=words[i];

                        for (Serv_Client_2015104 c:Server_2015104.client_sockets)
                            if(c!=null && c.count==index)
                            {
                                c.recv.writeUTF(s);
                                c.recv.flush();
                                bool=1;
                            }
                        int index1=Integer.parseInt(words[3]);
                        int bool1=0;
                        for (Serv_Client_2015104 c:Server_2015104.client_sockets)
                            if(c!=null && c.count==index1)
                            {

                                c.recv.writeUTF(s);
                                c.recv.flush();
                                bool1=1;
                            }
                        if(bool==0 || bool1==0)
                        {
                            recv_message = "Message to client X "+ Integer.toString(bool)+ "  Message to client Y "+Integer.toString(bool1);
                            recv.writeUTF(recv_message);
                        }
                    }
                }
                else if(words[0].equals("List") && words[1].equals("All"))
                {
                    String op="";
                    for (Serv_Client_2015104 c:Server_2015104.client_sockets)
                        if(c!=null)
                            op+="Client "+ Integer.toString(c.count)+"\n";
                    recv.writeUTF(op);
                    System.out.println("the List");
                }
                else
                {
                    recv_message=reverseit(sent_message);
                System.out.println("Server: "+recv_message);
                recv.writeUTF(recv_message);
                }
            }
            sent.close();
            recv.close();
            me.close();
        }
        catch(Exception e){
            //e.printStackTrace();
        }
    }
}
