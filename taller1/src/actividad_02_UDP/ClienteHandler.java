package actividad_02_UDP;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;

public class ClienteHandler extends Thread {

	// Variables usadas por el hilo para su funcionamiento
	private DatagramSocket socketUDP;
	byte[] buffer = null;
	String mensajeRecibido;

	// Constructor del propio hilo
	public ClienteHandler(DatagramSocket socketUDP) {
		this.socketUDP = socketUDP;
		buffer = new byte[1024];
	}

	@Override
	public void run() {
		try {
			do {
				// Preparamos la variable que almacenara el mensaje del cliente y la almacenamos
				DatagramPacket datagramaRecibido = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(datagramaRecibido);

				// Mostramos el mensaje
				this.mensajeRecibido = new String(datagramaRecibido.getData(), 0, datagramaRecibido.getLength());
				System.out.println(this.mensajeRecibido);

				// Obtenemos el puerto y la direccion del cliente
				int puertoCliente = datagramaRecibido.getPort();
				InetAddress direccion = datagramaRecibido.getAddress();

				// Preparamos el mensaje a mandar, y lo mandamos
				String mensajeEnviar = "¡Hola mundo - servidor!";
				buffer = mensajeEnviar.getBytes();
				DatagramPacket datagramaEnviado = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
				socketUDP.send(datagramaEnviado);

			} while (!mensajeRecibido.toLowerCase().equals("/salir"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
