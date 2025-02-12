package actividad_02_UDP;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {

	public static void main(String[] args) {

		// Declaramos las variables que usaremos en el servidor
		final int puerto = 5900;

		// Encendemos el servidor
		System.out.println("Iniciado el servidor UDP");
		try (DatagramSocket socketUDP = new DatagramSocket(puerto)) {
			
			// Constantemente acepta a los clientes que lo soliciten
			while (true) {
				new ClienteHandler(socketUDP).start();
			}

		} catch (SocketException ex) {
			ex.printStackTrace();
		}

	}

}
