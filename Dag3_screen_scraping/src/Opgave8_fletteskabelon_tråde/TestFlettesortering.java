package Opgave8_fletteskabelon_tråde;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFlettesortering {

	/////Prøv at se om den kan fixes.

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Random r=new Random();
		for (int i=0;i<1000000;i++) {
			list.add(Math.abs(r.nextInt()%10000));
		}

		System.out.println(list);

		Tråd_FletteSortering t1 = new Tråd_FletteSortering(list,0 ,list.size() / 2);
		Tråd_FletteSortering t2 = new Tråd_FletteSortering(list,(list.size() / 2) + 1 ,list.size() - 1);

		long l1,l2;
		l1 = System.nanoTime();
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		l2 = System.nanoTime();

		System.out.println();
		System.out.println(list);
		System.out.println();
		System.out.println("K�retiden i milisekunder var: " + (l2-l1)/1000000);
	}

}
