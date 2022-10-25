package Opgave6_tråde_hvert_3_sekundt;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Tråd1_strengFraTastatur extends Thread {

    private CommonClass commonClass;
    private Semaphore s;

    public Tråd1_strengFraTastatur(CommonClass commonClass, Semaphore s){
        super();
        this.commonClass = commonClass;
        this.s = s;
    }

    public void run(){
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("Hvad skal der skrives");
                commonClass.setOrd(scan.next());
            }
    }

}
