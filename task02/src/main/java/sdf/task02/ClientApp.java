package sdf.task02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ClientApp {

    String address;
    int port;

    public ClientApp(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String readStringResponse(ObjectInputStream ois) {
        String resp = "none";
        try {
            resp = ois.readUTF();
            System.out.println("server: " + resp);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error: no response");
        }
        return resp;
    }

    public void sendResponse(ObjectOutputStream oos, String resp) {
        try {
            oos.writeUTF(resp);
            System.out.println("Client: " + resp);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error: cannot send response");
        }
    }

    public void start() throws UnknownHostException, IOException {

        // connect to server
        Socket socket = new Socket(this.address, this.port);
        System.out.printf("Connected to server %s on port %d\n", this.address, this.port);

        // init IO streams
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

        // receive response
        String resp = this.readStringResponse(ois);

        // process response
        String[] respSplit = resp.split(" ");
        String reqId = respSplit[0];
        String[] numsStr = respSplit[1].split(",");

        // calculate average
        int[] nums = Arrays.stream(numsStr).mapToInt(Integer::parseInt).toArray();
        float denom = (float) nums.length;
        int sum = IntStream.of(nums).sum();
        float avg = sum / denom;
        System.out.printf("sum: %d, length: %f, avg: %f\n", sum, denom, avg); // sanity check

        // return response to server
        this.sendResponse(oos, reqId);
        this.sendResponse(oos, "Muhammad Syafiq Bin Md Yusof");
        this.sendResponse(oos, "syafiq.ysf@gmail.com");
        oos.writeFloat(avg);
        System.out.println("Client: "+ avg);
        oos.flush();

        // read boolean resp
        boolean result;
        result = ois.readBoolean();
        System.out.println("server: " + result);

        // print result and close IO streams and connection
        if(result) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAILED");
            String errMsg = this.readStringResponse(ois);
            System.out.println(errMsg);
        }
        ois.close();
        oos.close();
        socket.close();
    }

}
