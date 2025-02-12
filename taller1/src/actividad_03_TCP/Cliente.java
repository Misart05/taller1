package actividad_03_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		
		// Variables y objetos para determinar el puerto, la ip del host y las herramientas que se
		// usaran en la comunicación entre cliente-servidor
		Scanner sc = new Scanner(System.in);
		
		final int puerto = 5900;
		String server = "localhost";
		DataInputStream in;
		DataOutputStream out;
		
		try {
			// Declaramos el cliente conectando con el puerto y la ip del servidor
			Socket cliente = new Socket(server, puerto);
			
			// Declaramos los objetos input y output para comunicarnos con el servidor
			in = new DataInputStream(cliente.getInputStream());
			out = new DataOutputStream(cliente.getOutputStream());
			
			// Recopilamos el mensaje del cliente y lo mandamos al servidor
			System.out.println("Escriba el mensaje para el servidor: ");
			String mensaje = sc.nextLine();
			out.writeUTF(mensaje);
			
			// mientras el mensjae no sea "adios", lo mandamos al servidor, imprimimos el mensaje
			// del servidor en la consola del cliente y recopilamos otro mensaje para mandar
			do  {
				
				String mensajeServidor = in.readUTF();
				System.out.println(mensajeServidor);
				
				System.out.println("Escriba el mensaje para el servidor: ");
				mensaje = sc.nextLine();
				out.writeUTF(mensaje);
				
			} while (!mensaje.toLowerCase().equals("adios"));
			
			// Cuando el mensaje sea "adios", cerraremos el cliente y, por ende, el programa
			cliente.close();
			sc.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
