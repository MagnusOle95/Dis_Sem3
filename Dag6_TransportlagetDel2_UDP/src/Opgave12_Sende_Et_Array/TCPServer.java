package Opgave12_Sende_Et_Array;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(8990);
		ArrayList<Person> personer = new ArrayList<>();
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			modtagPersoner(personer,inFromClient);
			System.out.println(personer);
		}

	}

	public static ArrayList<Person> modtagPersoner(ArrayList<Person> arrayAfPersoner, BufferedReader instream) throws IOException {
		String personString = instream.readLine();
		System.out.println(personString);
		String[] personStringArray = personString.split(" ");

		for (int i = 0; i < personStringArray.length; i = i + 3) {
			arrayAfPersoner.add(new Person(Integer.parseInt(personStringArray[i]), personStringArray[i + 1], personStringArray[i + 2]));
		}
		return arrayAfPersoner;
	}
}
