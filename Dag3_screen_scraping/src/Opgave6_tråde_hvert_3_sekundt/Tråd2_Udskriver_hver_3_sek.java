package Opgave6_tråde_hvert_3_sekundt;

import java.util.concurrent.Semaphore;

public class Tråd2_Udskriver_hver_3_sek extends Thread {

    private CommonClass commonClass;
    private Semaphore s;

    public Tråd2_Udskriver_hver_3_sek(CommonClass commonClass, Semaphore s){
        super();
        this.commonClass = commonClass;
        this.s = s;
    }

    public void run() {
        while (true){
            try {
                this.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(commonClass.getOrd());
        }
    }
}
