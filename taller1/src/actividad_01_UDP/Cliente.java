package actividad_01_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

    	// Comentario para el commit
        // Declaramos variables importantes, el puerto del server y el buffer
        final int puerto = 5900;
        byte[] buffer = new byte[1024];

        try {

        	// Obtenemos la localizacion del servidor y declaramos un nuevo socket
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            DatagramSocket socketUDP = new DatagramSocket();
            
            // Declaramos un mensaje y transformamos el mensaje en bytes
            System.out.println("Escribe un mensaje:");
            String mensajeMandar = sc.next();
            buffer = mensajeMandar.getBytes();
            
            //Creamos el datagrama y lo mandamos al servidor
            DatagramPacket datagramaEnviado = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto);
            socketUDP.send(datagramaEnviado);

            do {
                
                // Preparamos la variable que almacenara el mensaje del servidor y recibimos el mensaje
                DatagramPacket datagramaRecibido = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(datagramaRecibido);

                // Preparamos el mensaje y lo mostramos
                String mensajeEnviar = new String(datagramaRecibido.getData(), 0, datagramaRecibido.getLength());
                System.out.println(mensajeEnviar);
                
                // Preparamos otro mensaje y ofrecemos la posibilidad de salir
                System.out.println("Escribe un mensaje (adios para salir):");
                mensajeMandar = sc.next();
            	
            	// Transformamos el mensaje en bytes
                buffer = mensajeMandar.getBytes();
                
                //Creamos el datagrama y lo mandamos al servidor
                datagramaEnviado = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto);
                socketUDP.send(datagramaEnviado);
            	
            } while (!mensajeMandar.toLowerCase().equals("adios"));
            
            socketUDP.close();
            sc.close();

        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}