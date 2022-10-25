package TCPandUDP.Opgave3_Client_server_tråde;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class OutToOtherUser_Tråd extends Thread{

    private BufferedReader inFromUser;
    private DataOutputStream outToOtherUser;

    public OutToOtherUser_Tråd(BufferedReader inFromUser, DataOutputStream outToOtherUser){
        this.inFromUser = inFromUser;
        this.outToOtherUser = outToOtherUser;
    }

    public void run() {
        while (true){
            try {
                String sentence = inFromUser.readLine();
                outToOtherUser.writeBytes(sentence + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}