package kr.co.sist.cinema.manager_movieinfo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.ManagerMenu;

/**
 * 영화 정보 관리 기능 실행
 * @author sehoon
 *
 */
public class MovieInfoManager extends FileRead {

	/**
	 * 영화 관리 기능 선택 및 실행
	 */
	public void menu() {
		System.out.println("                                   [영화정보관리]");
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("                                   1. 영화 정보 추가");
		System.out.println("                                   2. 영화 정보 삭제");
		System.out.println("                                   0. 종료");
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                   선택(번호) : ");
		
		int num = scan.nextInt();
		scan.nextLine(); // 엔터없애기
		
		while(num < 0 || num > 2) {
			
			System.out.println("                                   잘못입력하셨습니다. 다시 입력해주세요");
			System.out.println("                                   [영화정보관리]");
			System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
			System.out.println("                                   1. 최근 입력 영화 정보 열람");
			System.out.println("                                   2. 영화 정보 추가");
			System.out.println("                                   0. 종료");
			System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
			System.out.print("                                   선택(번호) : ");
		}
		
		if(num == 1 ) {
			movieInfoAdd();
			System.out.println("                                   엔터를 누르면 영화정보관리로 돌아갑니다.");
			pause();
			menu();
		}else if(num == 2) {
			movieDelete();
			System.out.println("                                   엔터를 누르면 영화정보관리로 돌아갑니다.");
			pause();
			menu();
		}else {
			ManagerMenu managerMenu = new ManagerMenu();
			try {
				managerMenu.ManagerInitialMenu();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 영화 정보 추가
	 */
	public void movieInfoAdd() {

		int movieUniqueNumber = 0;

		initialReader("movieinfo.dat");

		for (int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			movieUniqueNumber = Integer.parseInt(division[0]);
		}

		movieUniqueNumber++;

		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.print("                                   영화제목(등급)을 입력해주세요 : ");
		String movieTitle = scan.nextLine();
		System.out.print("                                   개봉일 입력해주세요(형식 : 2019-02-12) : ");
		String movieOpening = scan.nextLine();
		System.out.print("                                   상영시간 입력해주세요(형식 : 120분) : ");
		String movieTime = scan.nextLine();
		System.out.print("                                   감독을 입력해주세요 : ");
		String director = scan.nextLine();
		System.out.print("                                   배우를 입력해주세요(형식 : 김혜수, 전지현, 강소라) : ");
		String actor = scan.nextLine();
		System.out.print("                                   예고편을 입력해주세요. : ");
		String preview = scan.nextLine();
		System.out.print("                                   줄거리를 입력해주세요. : ");
		String plot = scan.nextLine();
		System.out.println("                                   ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "movieinfo.dat", true));) {
			writer.append(movieUniqueNumber + "■" + "2" + "■" + movieTitle + "■" + movieOpening + "■" + movieTime + "■"
					+ director + "■" + actor + "■" + preview + "■" + "0" + "■" + plot + "■" + " " + "■\r\n");
		} catch (FileNotFoundException e) {
			System.out.println("movieinfo.dat 경로 없음");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("========================================최근 입력 영화정보 5개 시작========================================");
		System.out.println("                                       최근 입력된 영화 데이터 5개");
		initialReader("movieinfo.dat");
		
		System.out.println("영화고유번호\t영화제목(등급)\t개봉일\t상영시간");
		for(int i = list.size()-1; i > list.size()-6; i--) {
			String[] division = list.get(i).split("\\■");
			System.out.printf("%s\t%s\t%s\t%s\n", division[0], division[2], division[3], division[4]);
		}
		
		System.out.println("========================================최근 입력 영화정보 5개 끝========================================");

	}

	/**
	 * 영화 정보 삭제
	 */
	public void movieDelete() {

		ArrayList<String> listModify = new ArrayList<String>();

		initialReader("movieinfo.dat");

		System.out.println("영화고유번호\t영화제목(등급)\t개봉일\t상영시간");
		for (int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			System.out.printf("                                   %s\t%s\t%s\t%s\n", division[0], division[2], division[3], division[4]);
		}

		System.out.print("                                   삭제할 영화의 고유번호를 입력해주세요 : ");
		String deleteNum = scan.nextLine();

		while (Integer.parseInt(deleteNum) < 1 || Integer.parseInt(deleteNum) > list.size()) {

			System.out.print("                                   없는 영화입니다.삭제할 영화의 고유번호를 다시 입력해주세요 : ");
			deleteNum = scan.nextLine();
		}

		for (int i = 0; i < list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			if (!(division[0].equals(deleteNum))) {
				listModify.add(list.get(i));
			}

		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "movieinfo.dat"));) {
			for (int i = 0; i < listModify.size(); i++) {
				writer.write(listModify.get(i) + "\r\n");
			}
		} catch (FileNotFoundException e) {
			System.out.println("                                   movieinfo.dat 경로 없음");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
