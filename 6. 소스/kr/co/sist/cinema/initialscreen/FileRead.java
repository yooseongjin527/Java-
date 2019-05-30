package kr.co.sist.cinema.initialscreen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 파일 경로 설정
 * 해당 경로에서 파일 읽어오기
 * @author sehoon
 *
 */
public class FileRead {

	public static final String PATH;
	protected ArrayList<String> list;
	protected Scanner scan;
	
	static {
		PATH = "D:\\class\\java\\cinema\\data\\";
	}
	
	public FileRead() {
		list = new ArrayList<String>();
		scan = new Scanner(System.in);
	}
	
	/**
	 * 매개변수(파일명.확장자)를 이용하여 해당 파일을 읽어옴
	 * @param fileName
	 */
	public void initialReader(String fileName) {

		this.list.clear();

		try (BufferedReader reader = new BufferedReader(new FileReader(PATH + fileName));) {

			String line = "";

			while ((line = reader.readLine()) != null) {
				this.list.add(line);
			}

		} catch (FileNotFoundException e) {
			System.out.println("경로 x");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 화면을 보여주기 위하여 엔터를 치기전에는 멈춤
	 */
	public void pause() {
		
		System.out.print("                                       계속 하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
	}
	
}
