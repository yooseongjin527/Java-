package kr.co.sist.cinema.snackstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;
/**
 * 물품 메뉴 UI
 * @author sehoon
 *
 */
public class UI {
	
	private ArrayList<StoreMenu> list2; //memo.dat의 복사본(*******)
	/**
	 * 기본 생성자
	 */
	public UI() {
		list2 = new ArrayList<StoreMenu>();
	}

	/**
	 * 메뉴 데이터를 읽어옴
	 */
	public void load() {
		//POPCORN.dat -> list 복사하기
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("D:\\class\\java\\cinema\\data\\메뉴판.dat"));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				//line(메모 1건) -> Memo 객체 1개
				
				StoreMenu t = new StoreMenu();
				t.setPopcornName(temp[0]);
				t.setPopcornPrice(temp[1]);
				t.setBeverageName(temp[2]);
				t.setBeveragePrice(temp[3]);
				t.setCoffeeName(temp[4]);
				t.setCoffeePrice(temp[5]);
				t.setComboName(temp[6]);
				t.setComboPrice(temp[7]);
				t.setHotdogName(temp[8]);
				t.setHotdogPrice(temp[9]);
				t.setNachoName(temp[10]);
				t.setNachoPrice(temp[11]);
				list2.add(t);
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}
	/**
	 * 메뉴데이터의 목록 보여줌
	 */
		public void Storelist() {
		
		//ui.title("메모 읽기");
		//ui.title(2);
		//ui.title(UI.LIST);
			
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                       매점에 들어오셨습니다.");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                                                                                            [메뉴판]");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("●팝콘●                          ●음료●                           ●커피●                            ●콤보●                                   ●핫도그●                      ●나쵸●");
		System.out.println();
		System.out.println("[품목명]              [가격]    [품목명]               [가격]    [품목명]                [가격]    [품목명]                       [가격]    [품목명]            [가격]    [품목명]          [가격]");
		
		for (StoreMenu t : list2) {
			
			System.out.printf("%s%s%s%s%s%s%s%s%s%s%s%s\n"
								, t.getPopcornName()
								, t.getPopcornPrice()
								, t.getBeverageName()
								, t.getBeveragePrice()
								, t.getCoffeeName()
								, t.getCoffeePrice()
								, t.getComboName()
								, t.getComboPrice()
								, t.getHotdogName()	
								, t.getHotdogPrice()
								, t.getNachoName()
								, t.getNachoPrice());

		}
		System.out.println();
		
	}
		
	public void end() {
		System.out.println("종료.");
	}

}
