package TCPandUDP.Opgave1_og_2_client_server_protokol_talk21s;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		boolean kør = false;
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 8989);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println("sig goddag til server");
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		sentence = inFromServer.readLine();
		System.out.println("Fra server: " + sentence);

		if (sentence.equalsIgnoreCase("Goddag")){
			kør = true;
		}
		else{
			System.out.println("Fra server: vil ikke snakke med dig");
		}

		while (kør) {
			sentence = inFromUser.readLine();
			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();

			if (modifiedSentence.equalsIgnoreCase("Farvel")) {
				kør = false;
			}else{
				System.out.println("Fra server: " + modifiedSentence);
			}
		}
		clientSocket.close();
	}
}


