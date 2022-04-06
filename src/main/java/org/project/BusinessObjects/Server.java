package org.project.BusinessObjects;

import org.project.DAO.MySqlStaffDAO;
import org.project.DAO.StaffDAOInterface;
import org.project.Exceptions.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            StaffDAOInterface IStaffDao = new MySqlStaffDAO();
            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber, IStaffDao)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;
        StaffDAOInterface IStaffDao;

        public ClientHandler(Socket clientSocket, int clientNumber, StaffDAOInterface IStaffDao)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

                this.IStaffDao = IStaffDao;

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            int res;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("DisplayById"))
                    {
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);
                        try {
                            IStaffDao.findStaffbyIDJSONoFormatting(id);
                            socketWriter.println(IStaffDao.findStaffbyIDJSONoFormatting(id));
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("DisplayAll"))
                    {
                        try {
                            IStaffDao.findAllStaffJSONNoFormatting();
                            socketWriter.println(IStaffDao.findAllStaffJSONNoFormatting());
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }
                    }
                    else if(message.startsWith("Triple")){
//                      res = Integer.parseInt(message.substring(7))*3;
//                      socketWriter.println(res);
                        String[] tokens = message.split(" ");
                        int num = Integer.parseInt(tokens[1]);
                        num = num*3;
                        socketWriter.println(num);
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}