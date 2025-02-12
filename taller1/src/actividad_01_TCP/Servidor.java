package actividad_01_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {

		// Variables para almacenar el servidor, el cliente, y los objetos para comunicarse
		ServerSocket server = null;
		Socket cliente = null;
		DataInputStream in;
		DataOutputStream out;
		
		// Puerto en el que se comunicarán
		final int puerto = 5900;
		
		try {
			// Iniciamos el servidor en el puerto y lo comunicamos por consola
			server = new ServerSocket(puerto);
			System.out.println("Servidor iniciado...\n");
			
			// Aceptamos al cliente en el server
			cliente = server.accept();
			
			// Declaramos los objetos para comunicarnos
			in = new DataInputStream(cliente.getInputStream());
			out = new DataOutputStream(cliente.getOutputStream());
			
			// Leemos el mensaje que manda el cliente al server
			String mensajeCliente = in.readUTF();
			
			// Mientras el mensaje no sea adios
			do {
				try {
					
					// Imprimimos el mensaje del cliente en pantalla
					System.out.println("Mensaje del cliente: ");
					System.out.println(mensajeCliente);
					
					// Mandamos un mensaje al cliente
					out.writeUTF("Mensaje mandado desde el servidor");
					
					// Volvemos a leer el mensaje
					mensajeCliente = in.readUTF();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} while (!mensajeCliente.toLowerCase().equals("adios"));
			
			// Cerramos el cliente y el server cuando el mensaje es "adios" y lo comunicamos por consola
			cliente.close();
			System.out.println("\nCliente desconectado...\n");
			server.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
