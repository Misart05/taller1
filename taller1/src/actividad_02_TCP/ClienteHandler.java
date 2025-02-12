package actividad_02_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteHandler extends Thread {
	
	// Declaramos el socket en el que almacenaremos los datos del cliente y los data input y output,
	// que serán usados para extaer los datos del servidor y mandar los mensajes a este mismo.
    private Socket socket;
	DataInputStream input;
	DataOutputStream output;

	// Constructor del propio hilo
    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
        	// Declaramos los datos de input y output con el stream del cliente
        	input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			
			// Leemos el mensaje del cliente
			String mensajeCliente = input.readUTF();
			
			do {
				try {
					
					// Imprimimos el mensaje del cliente
					System.out.println("Mensaje del cliente: ");
					System.out.println(mensajeCliente+"\n");
					
					// Mandamos un mensaje al cliente
					output.writeUTF("Mensaje mandado desde el servidor\n");
					mensajeCliente = input.readUTF();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			// Todo esto continuará mientras el cliente no escriba /salir
			} while (!mensajeCliente.toLowerCase().equals("/salir"));
			
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            synchronized (Servidor.clientes) {
            }
            
        }
    }
	
}