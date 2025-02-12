package actividad_02_TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	// Declaramos el puerto en el que se iniciará el servidor y un objeto que servira de sincronizador
	private static final int puerto = 5900;
	public static Object clientes;
	
	public static void main(String[] args) {
		
		// Declaramos el cliente
		Socket cliente = null;
		
		// Encendemos el servidor y asignamos el servidor al puerto
		System.out.println("Servidor de chat iniciado...\n");
        try (ServerSocket servidor = new ServerSocket(puerto)) {
        	
            while (true) {
            	
            	// Aceptamos a los clientes y creamos un hilo para que cada cliente interactue con el
            	cliente = servidor.accept();
                new ClienteHandler(cliente).start(); 
                
            }
            
        } catch (IOException e) {
        	 e.printStackTrace();
        }
		
	}
	
}