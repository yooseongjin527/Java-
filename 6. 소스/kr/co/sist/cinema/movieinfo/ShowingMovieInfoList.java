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
 * @author sehoon
 * 상영영화 정보를 담은 클래스
 *  
 **/
public class ShowingMovieInfoList extends FileRead {

	Scanner scan = new Scanner(System.in);
	String[] publishedDates;
	ArrayList<String> screeningCinemaInfo;
	private static ArrayList<Post>postlist = new ArrayList<Post>();
	
	/**
	 * 기본 생성자
	 */
	public ShowingMovieInfoList() {
		screeningCinemaInfo = new ArrayList<String>();
	}
	
	/**
	 * 영화정보 데이터에서 상영중인 영화정보 읽어오기
	 */
	public void load() {
			
		initialReader("movieinfo.dat");
		
		for(int i = 0; i < list.size(); i++ ) {
			String[] temp = list.get(i).split("\\■");
			
			if(temp[1].equals("1")) {
				screeningCinemaInfo.add(list.get(i));
			}
			
		}
		
			try {
				BufferedReader reader = new BufferedReader(new FileReader(PATH+"movieinfo.dat"));
				String line = null;
			
				while((line = reader.readLine()) != null) {
					String[]temp = line.split("\\■");
					
					//영화상태 -> 상영중(screeningCinemaInfo)
					if(temp[1].equals("2")) {
						screeningCinemaInfo.add(line);
					}
				}
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			//상영중인 영화만
			
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(PATH+"post.dat"));
				
				String line = null;
			
				while((line = reader.readLine()) != null) {
					String[]temp = line.split("\\■");
					
					Post post = new Post();
					post.setPostSerialNum(temp[0]);
					post.setSerialNum(temp[1]);
					post.setPublishedDate(temp[2]);
					post.setId(temp[3]);
					post.setHead(temp[4]);
					post.setBody(temp[5]);
					post.setIsPublic(temp[6]);
					post.setRating(temp[7]);
					
					postlist.add(post);
				}
				reader.close();
			} catch (Exception e) {
				// 
				System.out.println(e.toString());
			} 
			
		}//load()
	
	/**
	 * 영화정보 데이터에서 상영중인 영화의 영화명을 보여줌
	 */
	public void movieList() {
		
		boolean loop2 = true;
		while(loop2) {
			
			System.out.println();
			System.out.println("                                   ==============================");
				System.out.println("                                   상영영화정보\t");
				System.out.println("                                   ==============================");
				for(int i= 0; i<screeningCinemaInfo.size(); i++) {
					//division -> 상영중인 영화만 있는데서  "■"단위로 끊은거
					String[] division = screeningCinemaInfo.get(i).split("\\■");
					System.out.println("                                   ["+ (i+1) + "]" + division[2]);
					//Sytem.out.println(division[0]);
				}	
				System.out.println("                                   ==============================");
				System.out.println("                                   [0] 메뉴로 돌아가기");
				System.out.println("                                   [숫자] 영화정보보기");
				System.out.println("                                   ==============================");
				System.out.print("                                   선택 :");
				String listScan = scan.nextLine();
				
				//System.out.println(screeningCinemaInfo.get(Integer.parseInt("3")-1));  
				//입력받은 값이 '3'일때 숫자로 바꿔서 3-1=방위치
				
				//0일때 목록으로 돌아가기
				if(listScan.equals("0")) {
					GeneralMenu generalmenu = new GeneralMenu(InitialScreen.getId());
					generalmenu.generalMenuSelect();
					loop2 = false;
					
				//숫자선택하면 영화정보로 이동	
				}else {
					readFile(listScan);
				}
			}
			
	} // movieList	
	
	/**
	 * 상영중인 영화명을 고르면 자세히 보여주는 기능
	 * @param listScan
	 */
	public void readFile(String listScan) {
		
ArrayList<String> modifyList = new ArrayList<String>();
		
		for(int i= 0; i<screeningCinemaInfo.size(); i++) {
			String[] division = screeningCinemaInfo.get(i).split("\\■");
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
					System.out.println("                                   [무비포스트]");
					System.out.println("                                   ==============================");
					
					ArrayList<Post> postToSort = new ArrayList<Post>();
					for(Post post : postlist) {
						
						if(post.getSerialNum().equals(listScan)) {
							postToSort.add(post);
						}
					}
					printPosts(postToSort);

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
	
	/**
	 * 해당 영화의 포스트를 최신순으로 3개 보여줌
	 * @param postToSort
	 */
	public void printPosts(ArrayList<Post> postToSort) {
		
		if(postToSort.size() > 2) {
		
			for (int i = postToSort.size()-1; i > postToSort.size()-4; i--) {
	
				System.out.printf("                                   아 이 디 : %s\n", postToSort.get(i).getId());
				System.out.printf("                                   작성날짜 : %s\n", postToSort.get(i).getPublishedDate());
				System.out.printf("                                   평  점  : %s\n", postToSort.get(i).getRating());
				System.out.printf("                                   제  목  : %s\n", postToSort.get(i).getHead());
				System.out.printf("                                   내  용  : %s\n", postToSort.get(i).getBody());
				System.out.println("                                   ==============================");
				System.out.println();
				
			}
		}else {
			
			for (int i = postToSort.size()-1; i > 0; i--) {
				
				System.out.printf("                                   아 이 디 : %s\n", postToSort.get(i).getId());
				System.out.printf("                                   작성날짜 : %s\n", postToSort.get(i).getPublishedDate());
				System.out.printf("                                   평  점  : %s\n", postToSort.get(i).getRating());
				System.out.printf("                                   제  목  : %s\n", postToSort.get(i).getHead());
				System.out.printf("                                   내  용  : %s\n", postToSort.get(i).getBody());
				System.out.println("                                   ==============================");
				System.out.println();
				
			}
			
		}
	}

}//MovieInfoList 
