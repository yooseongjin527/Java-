package kr.co.sist.cinema.movieinfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.GeneralMenu;
import kr.co.sist.cinema.initialscreen.InitialScreen;

/**
 *  
 * @author 유경
 * 개봉예정영화 정보를 담은 클래스
 *  
 **/
public class FutureMovieInfoList extends FileRead {

	Scanner scan = new Scanner(System.in);
	ArrayList<String> futureCinemaInfo;
/**
 * 기본 생성자
 */
	public FutureMovieInfoList() {
		futureCinemaInfo = new ArrayList<String>();
	}
	
/**
 * 영화정보,포스트정보 읽어오기
 */
	public void load() {
			
		initialReader("movieinfo.dat");
		
		for(int i = 0; i < list.size(); i++) {
			String[]temp = list.get(i).split("\\■");
			
			if(temp[1].equals("2")) {
				futureCinemaInfo.add(list.get(i));
			}
			
		}//개봉예정중인 영화만
		
		}//load()
	
	/**
	 * 개봉 예정 영화들의 영화명을 보여줌
	 */
	public void movieList() {
	
		boolean loop2 = true;
		while(loop2) {

			System.out.println();
			System.out.println("                                   ==============================");
			System.out.println("                                   개봉예정영화정보\t");
			System.out.println("                                   ==============================");
			for(int i= 0; i<futureCinemaInfo.size(); i++) {
				String[] division = futureCinemaInfo.get(i).split("\\■");
				System.out.println("                                   ["+ (i+1) + "]" + division[2]);
			}
			System.out.println("                                   ==============================");
			System.out.println("                                   [0] 메뉴로 돌아가기");
			System.out.println("                                   [숫자] 영화정보보기");
			System.out.println("                                   ==============================");
			System.out.print("                                   선택 :");
			String listScan = scan.nextLine();
			
			//0일때 목록으로 돌아가기
			if(listScan.equals("0")) {
//				GeneralMenu generalmenu = new GeneralMenu(InitialScreen.getId());
//				generalmenu.generalMenuSelect();
				loop2 = false;
				
			//숫자선택하면 영화정보로 이동	
			}else {
				readFile(listScan);
			}
	}
		
	}//movieList()
		
	/**
	 * 개봉 예정 영화 목록에서 선택한 영화에 대한 자세한 정보를 보여줌
	 * @param listScan
	 */
	public void readFile(String listScan) {
		
		ArrayList<String> modifyList = new ArrayList<String>();
		
		for(int i= 0; i<futureCinemaInfo.size(); i++) {
			String[] division = futureCinemaInfo.get(i).split("\\■");
			modifyList.add( (i+1) + "■" + division[2] + "■" + division[3] + "■"
						+ division[4] + "■" + division[5] + "■" + division[6] + "■"
						+ division[7] + "■" + division[8] + "■" + division[9] + "■" );
		}
		
		for(int i = 0; i < modifyList.size(); i++) {
			
			String[] division = modifyList.get(i).split("\\■");	
			
			if(division[0].equals(listScan)) {
			
			boolean loop = true;
				while(loop) {
					
					String plot = "";
					String[] plotDivision = division[8].split("\\|");
					for(int k = 0; k < plotDivision.length; k++) {
						plot = plot + plotDivision[k] + "\r\n                                              ";
					}
					
					System.out.println("                                   ==============================");
					System.out.println();
					System.out.printf("                                   [영화명] : %s \n\r",division[1]);
				
					System.out.println("                                   ==============================");
					System.out.printf("                                   [개봉일] : %s\n\r"
										+ "                                   [상영시간] : %s\n\r"
										+ "                                   [감독] : %s\n\r"
										+ "                                   [배우] : %s\n\r"
										+ "                                   [예고편] : %s\n\r"
										+ "                                   [평점] : %s\n\r"
										+ "                                   [줄거리] : %s\n\r"
										,division[2]
										,division[3]
										,division[4]
										,division[5]
										,division[6]
										,division[7]
										,plot);

					System.out.println("                                   ==============================");
					
					System.out.println("                                   [0] 예고편 보기");
					System.out.println("                                   [1] 영화목록으로 돌아가기");
					System.out.println("                                   ==============================");
					System.out.print("                                   선택 :");
					String str = scan.nextLine();
					if(str.equals("0")) {
						try {
							Process notepad = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", division[6]).start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pause();
					}else if(str.equals("1")) {
						movieList();
						loop = false;
					}
				}

			}
		}
	}
		
}//MovieInfoList 

