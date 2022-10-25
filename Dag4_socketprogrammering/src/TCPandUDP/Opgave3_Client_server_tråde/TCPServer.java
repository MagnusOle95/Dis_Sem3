package TCPandUDP.Opgave3_Client_server_tråde;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		boolean kør;
		String sentence;
		String clientSentence;
		ServerSocket welcomeSocket = new ServerSocket(9899);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			kør = true;

			if (inFromClient.readLine().equalsIgnoreCase("goddag")) {
				System.out.println("Fra client: Goddag");
				sentence = inFromUser.readLine();
				if (sentence.equalsIgnoreCase("Goddag")) {
					outToClient.writeBytes("Goddag\n");
					Thread thread1 = new OutToOtherUser_Tråd(inFromUser, outToClient);
					thread1.start();
					Thread thread2 = new GetTekstFromOtherUser_Tråd(inFromClient);
					thread2.start();
				} else {
					outToClient.writeBytes("Nej\n");
				}

			}
		}
	}
}

