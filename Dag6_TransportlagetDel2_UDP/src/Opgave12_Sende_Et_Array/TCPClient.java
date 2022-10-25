package Opgave12_Sende_Et_Array;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClient {

	public static void main(String argv[]) throws Exception{
		String sentence;
		String bekræftelseFraServer;
		ArrayList<Person> personer = new ArrayList<>();

		personer.add(new Person(111,"Jens" ,"Christiansfeldt"));
		personer.add(new Person(222,"Morten" ,"Aarhus"));
		personer.add(new Person(333,"Lasse" ,"Aarhus"));
		personer.add(new Person(444,"kristina" ,"Friluftland"));
		personer.add(new Person(555,"Lucas" ,"???"));
		personer.add(new Person(666,"Magnus" ,"Dianalund"));

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("localhost",8990);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


		sendPersoner(personer,outToServer);
			clientSocket.close();
		}


	public static void sendPersoner(ArrayList<Person> arrayMedPersoner, DataOutputStream outstrem) throws IOException {
		String personstring = "";
		for (Person p : arrayMedPersoner){
			personstring = personstring + p + " ";
		}
		outstrem.writeBytes(personstring + '\n');
	}


}


