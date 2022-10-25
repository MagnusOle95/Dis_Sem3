package Opgave4_Wireshark_Stor_mængde_data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception{
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("10.10.139.216",6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		for (int i = 1; i < 100000; i++){
			outToServer.writeBytes( "Hej med dig din cowboy\n");
		}
		System.out.println("Hej");

		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}


