package actividad_03_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	// Variable estatica que ira sumando 1 por cada mensaje enviado
	private static int mensaje = 0;
	
	public static void main(String[] args) {
		
		// Variables y objetos para determinar el puerto y las herramientas que se
		// usaran en la comunicación entre cliente-servidor
		final int puerto = 5900;
		Socket cliente = null;
		DataInputStream input;
		DataOutputStream output;
		String mensajeCliente;
		
		// Arrancamos el servidor
		System.out.println("Iniciando el servidor...");
		try (ServerSocket server = new ServerSocket(puerto)) {
			
			// Acceptamos a un cliente
			cliente = server.accept();
			
			do {
				
				// Declaramos las herramientas necesaras para la comunicacion
				input = new DataInputStream(cliente.getInputStream());
				output = new DataOutputStream(cliente.getOutputStream());
				
				// Leemos el mensaje del cliente y lo imprimimos en pantalla
				mensajeCliente = input.readUTF();
				System.out.println("Mensaje del cliente: ");
				System.out.println(mensajeCliente);
				
				// Sumamos uno a la variable y devolvemos el mensaje del cliente junto a la variable
				mensaje = mensaje +1 ;
				output.writeUTF("Mensaje "+mensaje+": "+mensajeCliente);
				
			} while (!mensajeCliente.toLowerCase().equals("adios"));
			
			cliente.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
