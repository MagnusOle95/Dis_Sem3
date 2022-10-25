package Opgave3_Wireshark;

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
		clientSocket.setTcpNoDelay(true); //Indsat her.
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


		outToServer.writeBytes("HaHa");
		outToServer.writeBytes("HeHe");
		Thread.sleep(20);
		outToServer.writeBytes("HiHi");
		outToServer.writeBytes( "Ho Ho" + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}


