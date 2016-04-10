package example;

import java.rmi.*;
public class Server{

    public static void main(String[] argv) {
        System.setProperty("java.security.policy", "C:\\T01SD\\Server\\src" +
                "\\java.policy");

        System.setSecurityManager(new RMISecurityManager());
        try { //Iniciar a execução do registry no porto desejado
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
