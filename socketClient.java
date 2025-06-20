import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * John Peerzada
 * CSE 383
 * Client for cse383 Lab2/HW2
 * This client will:
 * I certify this is my own work!
 */
public class socketClient {

    int port = 0;
    String hostname = "";
    String muid = "";
    String formula="";

    Socket socket = null;
    PrintWriter out = null;

    BufferedReader in = null;
    private static Logger LOGGER = Logger.getLogger("info");
    FileHandler fh = null;



    /**
      main method - DO NO WORK IN PSVM (Public Static Void Main)
      just invoke other classes
      invocation java PGM serverName serverPort Values...
     */
    public static void main(String[] args) {

        int port = 0;
        String hostname = "";
        String muid = "";

        // Helper code to store all of the arguments after
        java.util.ArrayList<String> values = new java.util.ArrayList<String>();
        for (int i = 3;i < args.length;i++) {
            values.add(args[i]);
        }

        try {
                muid="peerzajb";
                hostname="10.32.1.92";
                port = 5002;

        } catch (Exception e) {

        }

        socketClient client = new socketClient(muid,port,hostname);
        client.process(values);
    }

    //Constructor
    socketClient(String muid,int port, String hostname) {

        // Set up logging
        try {
            fh = new FileHandler("client.log");
            LOGGER.addHandler(fh);
            LOGGER.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (IOException err) {
            System.err.println("Error - can't open log file");
        }

        LOGGER.info("ClientMAIN - Port = " + port + " hostname = " + hostname);
        this.hostname = hostname;
        this.port = port;
        this.muid = muid;
    }

    /**
      process - takes an arraylist of arguments
        each argument is type, number.
     */
    public void process(java.util.ArrayList<String> values) {
        boolean result = false;
        String greeting = "";
        String response = "";
        for (int retry = 1; retry > 0 && !result; retry--) {
            try {
                LOGGER.info("Connecting");
                connect();
                // TODO1 Send your MUID (not the empty string)
                sendString("peerzajb");

                greeting = readResponse();
                sendValues(values);
                response = readResponse();
                result = true;
                socket.close();
            } catch (IOException err) {
                System.out.println(err.toString());
                LOGGER.log(Level.SEVERE,"error during connection", err);
            }
        }
        if (result) {
            System.out.println("Success");
            System.out.println("Greeting => " + greeting);

            System.out.println("Formula  => " + formula);
            System.out.println("Answer   => " + response);


        } else {
            System.out.println("Failed");
        }
    }

    /**
        connects to server.
     */
    public void connect() throws IOException {
        LOGGER.log(Level.INFO,"Connect => hostname = " + hostname + " port = " + port);
        socket = new Socket(hostname,port);
        socket.setSoTimeout();
        socket.setTcpNoDelay();
        */

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }



    /**
        send values to server.
     */
    public void sendValues(java.util.ArrayList<String> values)
        throws IOException {
        // System.out.println("In sendvalues");
        java.util.Iterator<String> itr = values.iterator();
        while (itr.hasNext()) {
            String data=itr.next();

            /* TODO2 uncomment line below and add the current element being sent to formula
                formula +=
            */
            sendString(data);
        }
        sendString("=");


    }


    /**
      send string values to server.
     */
    public void sendString(String data) throws IOException {

        out.write(data + "\n");
        out.flush();

    }

    /**
      read response.
     */
    public String readResponse() throws IOException {
        String response = "";
        response = in.readLine();
        return response;
    }
}