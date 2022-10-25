package TCPandUDP.Opgave3_Client_server_tråde;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		boolean kør = true;
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 9899);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


		System.out.println("sig goddag til server");
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');

		if (sentence.equalsIgnoreCase("Goddag")) {
			sentence = inFromServer.readLine();
			System.out.println("Fra server: " + sentence);

			if (sentence.equalsIgnoreCase("Goddag")) {
				Thread thread1 = new OutToOtherUser_Tråd(inFromUser, outToServer);
				thread1.start();

				Thread thread2 = new GetTekstFromOtherUser_Tråd(inFromServer);
				thread2.start();

			} else {
				System.out.println("Fra server: Vil ikke snakke med dig");
				clientSocket.close();
			}
		} else {
			clientSocket.close();

		}
	}
}


