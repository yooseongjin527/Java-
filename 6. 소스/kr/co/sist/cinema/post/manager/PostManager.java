package kr.co.sist.cinema.post.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.ManagerMenu;
import kr.co.sist.cinema.initialscreen.UImanagerLogin;
/**
 * 포스트 관리
 * @author sehoon
 *
 */
public class PostManager extends FileRead {
	
	private int count = 1;
	private Scanner scan;
	public PostManager() {
		scan = new Scanner(System.in);
		
	}
	/**
	 * 포스트 관리 기능 선택하여 실행
	 */
	public void PostManagerStart() {
		
		System.out.println("프로그램 시작");
		
		boolean loop = true;
		
		while(loop) {
			try {
				UIpost.begin();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				System.out.println("[무비포스트 열람]");
				postView();
				pause();
			}else if (input.equals("2")) {
				postDelete();
				pause();
			}else if (input.equals("0")) {
				try {
					ManagerMenu managerMenu = new ManagerMenu();
					managerMenu.ManagerInitialMenu();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
		}
	}//시작

	/**
	 * 포스트 목록 보기
	 */
	public void postView() {

		System.out.println("[글 고유번호]\t[영화명고유번호]\t[작성시간]\t[아이디]\t[제목]\t[노출여부]\t[평점]\t[내용]");
		
		initialReader("post.dat");
		for(int i=0; i<list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			
			System.out.printf("%s\t\t%s\t\t\t%s\t%s\t%s\t"
								+ "%s\t%s\t%s\r\n"
								, division[0], division[1], division[2], division[3], division[4]
								, division[6], division[7], division[5]);
		
		}
		
	}//post열람
		
	/**
	 * 포스트 삭제
	 */
	public void postDelete() {
		
		ArrayList<String> list2 = new ArrayList<String>();// 무비포스트 정보를 새로 넣을 배열
		
		System.out.println("[무비포스트 삭제]");
		
		postView();
		
		System.out.print("식별 코드(글 번호) : ");
		String iNum = scan.nextLine();
		
		initialReader("post.dat");
		
		String noiNum = "";
		for(int i=0; i<list.size(); i++) {
			String[] division = list.get(i).split("\\■"); 
			
			if(iNum.equals(division[0])) {
				noiNum = division[0];
			}else {
				list2.add(list.get(i));
			}
			
		}//for
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "post.dat"));
		
			for(int i=0; i<list2.size(); i++) {
				writer.write(list2.get(i) + "\r\n");
			}
			
			writer.close();
			
		}catch (FileNotFoundException e) {
			System.out.println("post.dat 파일 없음");
		}catch (Exception e) {
			System.out.println();
		}
		if(!noiNum.equals("")) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패(해당 데이터의 식별 번호가 없습니다.)");
		}
	}//post 삭제
	
}//postManager
