package TCPandUDP.Opgave1_og_2_client_server_protokol_talk21s;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		boolean kør;
		String sentence;
		String clientSentence;
		ServerSocket welcomeSocket = new ServerSocket(8989);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			kør = false;

			if (inFromClient.readLine().equals("goddag")){
				System.out.println("Fra client: Goddag");
				sentence = inFromUser.readLine();
				if (sentence.equalsIgnoreCase("Goddag")){
					outToClient.writeBytes("Goddag\n");
					kør = true;
				}
				else{
					outToClient.writeBytes("Nej\n");
				}

				while (kør) {
					clientSentence = inFromClient.readLine();
					System.out.println("Fra client: " + clientSentence);


					if (clientSentence.equalsIgnoreCase("Farvel")){
						outToClient.writeBytes("Farvel\n");
						kør = false;
					}
					else{
						sentence = inFromUser.readLine();
						outToClient.writeBytes(sentence + "\n");
					}

				}
			}
		}
	}

}
