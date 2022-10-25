package Opgave6_tråde_hvert_3_sekundt;

import java.util.concurrent.Semaphore;

public class App {

    public static void main(String[] args) {
        CommonClass cc = new CommonClass("Frank");
        Semaphore s = new Semaphore(1);
        Tråd1_strengFraTastatur t1 = new Tråd1_strengFraTastatur(cc,s);
        Tråd2_Udskriver_hver_3_sek t2 = new Tråd2_Udskriver_hver_3_sek(cc,s);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
