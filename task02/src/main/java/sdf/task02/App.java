package sdf.task02;

import java.io.IOException;
import java.net.UnknownHostException;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Connecting to server.." );
        ClientApp client = new ClientApp("task02.chuklee.com", 80);
        
        try {
            client.start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
