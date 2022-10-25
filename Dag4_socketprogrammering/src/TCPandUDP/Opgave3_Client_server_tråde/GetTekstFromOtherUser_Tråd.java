package TCPandUDP.Opgave3_Client_server_tråde;

import java.io.BufferedReader;
import java.io.IOException;

public class GetTekstFromOtherUser_Tråd extends Thread {

    private BufferedReader inFromOtherUser;

    public GetTekstFromOtherUser_Tråd(BufferedReader inFromOtherUser){
        this.inFromOtherUser = inFromOtherUser;
    }

    public void run() {
        while (true){
            try {
                String clientSentence = inFromOtherUser.readLine();
                System.out.println("Fra User: " + clientSentence);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
