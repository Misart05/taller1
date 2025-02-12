package actividad_01_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {

    public static void main(String[] args) {

    	// Declaramos las variables que usaremos en el servidor
        final int puerto = 5900;
        byte[] buffer = new byte[1024];

        try {
        	
        	// Declaramos la conexion con el cliente
            System.out.println("Iniciado el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(puerto);
            
            String mensajeRecibido;

            do  {
                
                // Preparamos la variable que almacenara el mensaje del cliente y la almacenamos
                DatagramPacket datagramaRecibido = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(datagramaRecibido);
                
                // Mostramos el mensaje
                mensajeRecibido = new String(datagramaRecibido.getData(), 0, datagramaRecibido.getLength());
                System.out.println(mensajeRecibido);

                // Obtenemos el puerto y la direccion del cliente
                int puertoCliente = datagramaRecibido.getPort();
                InetAddress direccion = datagramaRecibido.getAddress();

                // Preparamos el mensaje a mandar, y lo mandamos
                String mensajeEnviar = "¡Hola mundo - servidor!";
                buffer = mensajeEnviar.getBytes();
                DatagramPacket datagramaEnviado = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                socketUDP.send(datagramaEnviado);
                
            } while (!mensajeRecibido.toLowerCase().equals("adios"));
            
            socketUDP.close();

        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
