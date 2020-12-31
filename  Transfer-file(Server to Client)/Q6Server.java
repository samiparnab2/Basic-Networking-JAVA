
import java.util.Scanner;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
class Q6ServerRun extends Thread{
     Socket socket;
     BufferedReader socketInput;
     PrintWriter socketOutput;
    Q4ServerRun(Socket s) throws Exception
    {
        socket=s;
        socketInput= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOutput=new PrintWriter(socket.getOutputStream(),true);
    }
   void closeSocket() throws Exception
   {
       socketOutput.println("closed");
       socket.close();
   }
    public void run()
    {
        try{
            String ip;
            while(true)
            {
                ip=socketInput.readLine();
                if(ip.equals("exit"))
                {
                    socket.close();
                    break;
                }
                runCommand(ip);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    void runCommand(String cm)
    {
         String command[]=cm.split(" ",0);
         if(command[0].equals("download"))
         {
             File f=
         }
    }
    
}
class ServerControl implements Runnable
{
    int port=9900;
    Scanner sc=new Scanner(System.in);
    ServerSocket ss;
    ArrayList<Q4ServerRun> connections=new ArrayList<Q4ServerRun>();
    void serverStart() throws Exception
    {
            new Thread(this).start();
            ss=new ServerSocket(port);
            while(true)
            {
                connections.add(new Q4ServerRun(ss.accept()));
                connections.get(connections.size()-1).start();   
            }
    }
    
    public void run()
    {
        try{
            while(!sc.nextLine().equals("q")){}
            for(Q4ServerRun i : connections)
            {
                i.closeSocket();
            }
            ss.close();
            System.exit(1);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
}
class Q6Server {

    public static void main(String[] args)  {
        try{
        new ServerControl().serverStart();
        }
        catch(Exception e)
        {
           e.printStackTrace(System.out);
        }
    }
}
