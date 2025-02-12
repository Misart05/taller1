package actividad_02_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	// Declaramos la direccion del servidor y el puerto
	private static final String server = "localhost";
	private static final int puerto = 5900;
	
	public static void main(String[] args) {
		
		// Declaramos la variable del escaner que recopilará los datos de teclado del usuario, los DataInput y los
		// DataOutput
		DataInputStream input;
		DataOutputStream output;
		Scanner sc;
		
		// Creamos una conexion con el servidor haciendo uso del puerto
		try (Socket socket = new Socket(server, puerto)) {

			// Llenamos las variables con su información correspondiente
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			sc = new Scanner(System.in);
			
			// Recopilamos los datos del cliente y los mandamos al servidor
			System.out.print("Escriba el mensaje para el servidor: ");
			String mensaje = sc.nextLine();
			output.writeUTF(mensaje);
			
			do  {
				// Leemos el mensaje del servidor y volvemos a recopilar los datos del cliente
				// para volverlos a mandar al servidor. Esto si se detecta que el mensaje no es /salir
				String mensajeServidor = input.readUTF();
				System.out.println(mensajeServidor+"\n");
				
				System.out.print("Escriba el mensaje para el servidor: ");
				mensaje = sc.nextLine();
				output.writeUTF(mensaje);
				
			} while (!mensaje.toLowerCase().equals("/salir"));
			
			sc.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}