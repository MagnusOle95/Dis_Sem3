package Opgave7_screen_scaping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Opgave7_1_ScreenSraping_valutakurser {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.valutakurser.dk/");
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        String str;
        while ((str = in.readLine()) != null) {
            if (str.contains("USD") && str.contains(">-0,5<!")){
                String kurs = str.substring(279,285);
                System.out.println("100 USD koster: " + kurs + "Kr");
            }

        }
        in.close();

    }


}
