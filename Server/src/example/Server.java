package example;

<<<<<<< HEAD
import java.io.*;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
>>>>>>> 0685093d3b0491b8d48aedd3f4e543b059988bc3
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
public class Server{

    public void knockKnockServer() {
        int portNumber = 4444;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
<<<<<<< HEAD
          /*  KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine); */
=======
            clientSocket = serverSocket.accept();
          //  clientSocket.
>>>>>>> 0685093d3b0491b8d48aedd3f4e543b059988bc3
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        System.setProperty("java.security.policy", "C:\\T01SD\\Server\\src" +
                "\\java.policy");

        System.setSecurityManager(new RMISecurityManager());
        try { //Iniciar a execução do registry no porto desejado
         //   java.rmi.registry.LocateRegistry.createRegistry()
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (Exception e) {
            System.out.println("Exception starting RMI registry:");
            e.printStackTrace();
        }

        try {
            Impl implementaInterface = new Impl("Impl");
            System.out.println("Servidor está OK");
        } catch (Exception e) {
            System.out.println("Erro no servidor " + e);
        }
    }
}
