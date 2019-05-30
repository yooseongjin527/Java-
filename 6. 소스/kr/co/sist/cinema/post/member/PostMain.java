package kr.co.sist.cinema.post.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;
import kr.co.sist.cinema.initialscreen.GeneralMenu;
import kr.co.sist.cinema.initialscreen.InitialScreen;
import kr.co.sist.cinema.manager_movieinfo.Movie;
import kr.co.sist.cinema.post.manager.Post;
/**
 * 영화 포스트 기능
 * @author sehoon
 *
 */
public class PostMain extends FileRead {
   
   public static Scanner scan;
   public static String POST;
   public static String MOVIEINFO;
   public static String BANNEDWORD;
   public static String bodyFinal;
   private static String inputBannedWord;
   public static UI uiPost;

   public static ArrayList<Post> posts;
   public static ArrayList<Movie> movies; 
   public static ArrayList<String> bannedWords;
   public static ArrayList<String> inputBody; 
   public static Movie movie;
   
   static {
      scan = new Scanner(System.in);
      POST = "D:\\class\\java\\cinema\\data\\post.dat";
      MOVIEINFO = "D:\\class\\java\\cinema\\data\\movieinfo.dat";
      BANNEDWORD = "D:\\class\\java\\cinema\\data\\bannedWord.dat";
      bodyFinal = "";
      uiPost = new UI();

      posts = new ArrayList<Post>();
      movies = new ArrayList<Movie>();
      bannedWords = new ArrayList<String>();
      inputBody= new ArrayList<String>();
      inputBannedWord = "";
      movie = new Movie();
   }
   /**
    * 포스트 기능 선택하기
    */
   public void PostWrite() {
	   
       PostMain postManager = new PostMain();
       uiPost.begin();
       
       
       boolean loop = true;
       
       postManager.load();
       while(loop) {
          
      	 uiPost.menu();
          
          switch(scan.nextLine()) {
          case "0":
        	 GeneralMenu generalmenu = new GeneralMenu(InitialScreen.getId());
  			generalmenu.generalMenuSelect();
             loop = false;
             break;
          case "1":
             postManager.writePost();
             break;
          case "2":
             
             boolean loop2 = true;
             while (loop2) {
          	   uiPost.myPostMenu();
                
                switch(scan.nextLine()) {
                case "1":
                   postManager.printMyPost();
                   break;
                case "2":
                   postManager.editPost();
                   break;
                case "3":
                   postManager.deletePost();
                   break;
                case "0":
                   loop2 = false;
                   break;
                   }
             }
          }
          postManager.save();
       }
       
   }
   /**
    * 포스트 데이터 읽어오기
    */
   public void load() {
         
         try {
            
            BufferedReader reader = new BufferedReader(new FileReader(POST));
            
            String line = null;
            while ((line = reader.readLine()) != null) {
               
               String[] temp = line.split("■");
               
               Post post = new Post();
               
               post.setPostSerialNum(temp[0]);
               post.setMovieSerialNum(temp[1]);
               post.setPublishedDate(temp[2]);
               post.setId(temp[3]);
               post.setHead(temp[4]);
               post.setBody(temp[5]);
               post.setIsPublic(temp[6]);
               post.setRating(temp[7]);
               
               posts.add(post);
               
            }
            
            reader.close();
            
         } catch (Exception e) {
            System.out.println(e.toString() + "load");
         }
         
         try {
            BufferedReader reader = new BufferedReader(new FileReader(MOVIEINFO));
            
            String line = null;
            
            while ((line = reader.readLine()) != null) {
               
               String[] temp = line.split("■");
               
               Movie movie = new Movie();
               movie.setSerialNum(temp[0]);
               movie.setTitle(temp[2]);
               movie.setDirector(temp[5]);
               
               movies.add(movie);
            }
            
            reader.close();
            
         } catch (Exception e) {
            System.out.println(e.toString() + "movieinfo_load");
         }
         
         try {
            BufferedReader reader = new BufferedReader(new FileReader(BANNEDWORD));
      
            String line = null;
            
            while ((line = reader.readLine()) != null) {
               String[] temp = line.split("■");
               bannedWords.add(temp[1]);
            }
            
            reader.close();
            
         } catch (Exception e) {
            System.out.println(e.toString() + "bannedWord_load");
         }
      }
   /**
    * 접속한 회원의 포스트 삭제
    */
   public void deletePost() {
      //회원 아이디 받아 글 출력하기
	   uiPost.title(UI.DELETE);
      String id = InitialScreen.getId();
      //TODO 아이디 받아오기
      
      int countSearchResult = 1;
      ArrayList<Post> searchResult = new ArrayList<Post>();
   // 사용자가 수정할 글을 선택하기위해 필요한 정보만을 출력
      System.out.println("                                       [번호] [영화제목]\t\t[작성자]\t\t[작성일자]\t\t[평점]    [제목]\t\t[내용]");
      for (Post post : posts)   {
      
         if (post.getId().equals(id)
        		 && post.getIsPublic().equals("노출")) {
         
            System.out.printf("                                        %d.   %s\t%s  \t %s   \t%s점 \t  제목 : %s \t\t내용 : %s\n"
                           ,countSearchResult
                           ,movies.get(Integer.parseInt(post.getMovieSerialNum())-1).getTitle()
                           ,post.getId()
                           ,post.getPublishedDate()
                           ,post.getRating()
                           ,post.getHead()
                           ,post.getBody()
            			   ); 
            
            searchResult.add(post);
            countSearchResult++;
         }
      }
      
      //글 노출 여부 "삭제"로 변경
      System.out.print("                                       삭제할 글 번호 입력 : ");
      
      // 임시 ArrayList에서의 index = 사용자 입력값-1
      int index = Integer.parseInt(scan.nextLine())-1;
      
      // 고유번호를 통해 원본 ArrayList에서의 수정할 post index (index = 글 고유번호 -1)
      System.out.println("                                       원본포스트의 제목 : " + searchResult.get(index).getHead());
      int postToEditSerialNum = Integer.parseInt(searchResult.get(index).getPostSerialNum());
      Post originalPost = posts.get(postToEditSerialNum-1);
     
      //포스트 삭제
      originalPost.setIsPublic("삭제"); 
      System.out.println("                                        " + originalPost.getIsPublic());
      uiPost.pause(UI.DELETE);
   }
   
   /**
    * 작성할 포스트 영화가 있는지 비교
    */
   public void writePost() {
      
      Post post = new Post();
      setMovie(post);
      
      if (post.getMovieSerialNum() != null) {
         setOtherThanMovie(post);
      }
      
      if (post.getBody() != null) { 
         posts.add(post);
      }
   }
   /**
    * 작성한 포스트 데이터에 입력
    * @param movieSerialNum 영화고유번호
    * @param rating 평점
    */
   public void postUpdate(String movieSerialNum, String rating) {
	   
//	   System.out.println("                                       영화 고유번호 : "+movieSerialNum + "평점 : " + rating);
	   
	   ArrayList<String> list2 = new ArrayList<String>();
	   
	   int count = 1;
	   int sum = Integer.parseInt(rating);
	   
	   initialReader("post.dat");
	   
	   for(int i = 0; i < list.size(); i++) {
		   String[] division = list.get(i).split("\\■");
		   if(division[1].equals(movieSerialNum) ) {
			   sum += Integer.parseInt(division[7]);
			   count++;
		   }
	   }
	   initialReader("movieinfo.dat");
	   
	   try(
			   BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "movieinfo.dat"));
			   )
	   {
		   for(int i = 0; i < list.size(); i++) {
			   String[] division = list.get(i).split("\\■");
			   if(division[0].equals(movieSerialNum) ) {
				   list2.add(division[0] + "■" + division[1] + "■" + division[2] + "■" + division[3] + "■" + division[4]
						   + "■" + division[5] + "■" + division[6] + "■" + division[7] + "■" + String.format("%.1f", (double)(sum/count))
						   + "■" + division[9]  + "■" + division[10]  + "■" 
						   );
			   }else {
				   list2.add(list.get(i));
			   }
			   
		   }
		   
		   for(int i = 0; i < list2.size(); i++) {
			   writer.write(list2.get(i) + "\r\n");
		   }
		   
	   }
	   catch(FileNotFoundException e) {
		   System.out.println("movieinfo.dat 경로 없음");
	   }
	   catch(IOException e) {
		   e.printStackTrace();
	   }
	   
   }

   /**
    * 포스트를 쓸 영화 검색
    * @param post 포스트 데이터가 저장된 객체
    */
   public void setMovie(Post post) {
      
      Scanner scan = new Scanner(System.in);
      
      // 영화제목 검색 결과를 임시로 저장할 ArrayList searchResult와 넘버링 인덱스 index
      ArrayList<Movie> searchResult = new ArrayList<Movie>();
      int countList = 1;
      
      uiPost.title(uiPost.WRITE);
      
      // 사용자 입력한 검색어에서 띄어쓰기 제거
      System.out.print("                                       영화 제목 검색(모를시 엔터를 치면 목록이 나옵니다.) : ");
      String inputTitle = scan.nextLine().replace(" ","");
      System.out.println();
      System.out.println("                                       [번호] [영화 제목]      [감독] ");
      
   
      // 영화제목이 일치하는 결과부터 상위에 출력
      String accurateTitle = "";
	  System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");

      for (Movie movie : movies) {
         
         String title = movie.getTitle().replace(" ", "");
         if (title.equals(inputTitle)) {
            System.out.printf("                                       %d.     %s      %s\n"
                  , countList
                  , movie.getTitle()
            	  , movie.getDirector());
            searchResult.add(movie);
            countList++;
            accurateTitle = movie.getTitle();
         } 
      }
      
      // 영화제목이 일부 일치하는 결과 출력
      for (Movie movie : movies) {
         
         String title = movie.getTitle().replace(" ", "");
         
         if (title.indexOf(inputTitle) != -1) {
        	 if( !(movie.getTitle().equals(accurateTitle) ) ) {
	            System.out.printf("                                       %d.     %s      %s\n"
	                  , countList
	                  , movie.getTitle()
	                  , movie.getDirector());
	            searchResult.add(movie);
	            countList++;
        	 }
         } 
      }
	  System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");

      if (countList > 1) {
      
    	 System.out.println();
         System.out.println("                                       <<검색 완료>>");
         System.out.println();
         System.out.print("                                       찾는 영화 번호 입력: ");
         
         // index는 0부터 시작, searchResult 출력은 1부터 시작하므로 사용자가 입력하는 정수는 실제 index + 1
         int index = Integer.parseInt(scan.nextLine())-1;
         post.setMovieSerialNum(searchResult.get(index).getSerialNum());
         // 영화 고유 번호 set 완료
         
         System.out.println("                                       글 작성을 계속합니다. 엔터를 입력하세요.");
         
      } else {
         System.out.println("                                       찾는 영화가 없습니다. 계속하려면 엔터를 입력하세요.");
         
      }
      scan.nextLine();
   }

   /**
    * 포스트 내용 작성하기
    * @param post 포스트 데이터가 저장된 객체
    */
   public void setOtherThanMovie(Post post) {
	   
	  //글 고유번호 set
	  post.setPostSerialNum(String.format("%d", posts.size() + 1)); 
	  // 작성일자 set
      Calendar today = Calendar.getInstance();
      post.setPublishedDate(String.format("%tF", today));
      
      //회원 아이디 set
      // TODO 회원 아이디 받아오기
      post.setId(InitialScreen.getId());
      
      //노출 여부 set
      post.setIsPublic("노출");
      
      // 평점 set
      
      System.out.print("                                       평점 입력 (1~5) : "  );
      String rating = scan.nextLine();
      if (rating.equals("1")
            || rating.equals("2")
            || rating.equals("3")
            || rating.equals("4")
            || rating.equals("5")) {
         post.setRating(rating);
      }
      
      setHead(post);
      if (post.getHead() != null) {
         
//         setBody(post);
	      collectInput();
	      if (isAllClean(inputBody)) {
	            post.setBody(bodyFinal);
	         } else {
	        	 System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	        	 System.out.println("                                       금지어가 포함되어 있습니다. 다시 입력해주세요.");
	        	 System.out.println("                                       금지어 :" + inputBannedWord);
	           	 System.out.println("                                       글 작성을 계속하시겠습니까? (1 : 예, 2 : 아니오");
	        	 System.out.print("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	        	 if (scan.nextLine().equals("1")) {
	        		 collectInput();
	        		 
	        	 } 
	         }
      }
      postUpdate(post.getMovieSerialNum(), post.getRating() );
      
   }

   /**
    * 포스트 제목 작성
    * @param post
    */
   private void setHead(Post post) {
      // 제목 set
      boolean isBanned = true;
      while(isBanned) {
         
//         Scanner scan = new Scanner(System.in);
         System.out.print("                                       제목 입력 : ");
         String input = scan.nextLine();
         
         if(input.equals("종료")) {
            isBanned = false;
         }
         
         // isLineClean = true 일 때 (금지어가 없었을 때) 루프탈출
         if(isLineClean(input, bannedWords)) {
            post.setHead(input);
            isBanned = false;
         } else {
            
       	   	System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
            System.out.println("                                       금지어가 포함되어 있습니다. 다시 입력해주세요.");
      	  	System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
         }
         
      } // while
      System.out.println();
      System.out.println("                                       <<제목 입력 완료>>");
   }
   
   /**
    * 포스트 내용 작성
    */
   private void collectInput() {
	   
	  System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	  System.out.println("                                       내용을 여러줄로 입력할 수 있습니다.");
      System.out.println("                                      \'완료\'를 입력시 글이 작성됩니다.");
      System.out.println("                                       ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
      System.out.println("                                       내용 입력 : ");
	     
	         
         // 다중라인 입력
         boolean isWriting = true;
         
         while(isWriting) {
        	System.out.printf("                                       ");
            String input = scan.nextLine();
            
            // 예약어 "완료" 입력시 while(isWriting) 탈출
            if(input.equals("완료")) {
               isWriting = false;
               break;
            }
            
            inputBody.add(input);
         }
	      
   }
   
   /**
    * 금지어가 있는지 비교
    * @param line 작성한 글
    * @param bannedWords 금지어
    * @return
    */
   private boolean isLineClean(String line, ArrayList<String> bannedWords) {
      
      boolean isLineClean = true;
      
      for (String bannedWord : bannedWords) {
         
         if (line.indexOf(bannedWord) > -1) {
        	 
            // 하나의 금지어라도 line과 일치할 때 isLineClean = false
            inputBannedWord=bannedWord;
            isLineClean = false;
            
         }
      } 
      
      return isLineClean;
   }

   /**
    * 금지어가 없으면 내용 누적
    * @param inputBody 작성 내용
    * @return
    */
   private boolean isAllClean(ArrayList<String> inputBody) {
      
      boolean isAllClean = true;
      
      
      for (String line : inputBody) {
         // 한 줄에 금지어가 있는지 검사
         if (isLineClean(line, bannedWords)) {
            
            // 한 줄에 금지어가 없으면 누적
            bodyFinal += line + " ";
            
         } 
         // 금지어가 한 줄에라도 있을시 while(isBad) 탈출 X 내용 입력 계속
         else {
            isAllClean = false;
            break;
         }
      }
      
      return isAllClean;
   }
   
   /**
    * 나의 포스트 내용 수정하기
    */
   public void editPost() {
      //회원 아이디 받아 글 출력하기
	   uiPost.title(UI.EDIT);
//      String id = InitialScreen.getId();
      String id = InitialScreen.getId();
      //TODO 아이디 받아오기
      
      int countSearchResult = 1;
      ArrayList<Post> searchResult = new ArrayList<Post>();
      
      System.out.println("                                       [번호] [영화제목]\t\t[작성자]\t\t[작성일자]\t\t[평점]    [제목]\t\t[내용]");
      // 사용자가 수정할 글을 선택하기위해 필요한 정보만을 출력
      for (Post post : posts)   {
      
         if (post.getId().equals(id)
        		 && post.getIsPublic().equals("노출")) {
         
            System.out.printf("                                        %d.   %s\t%s  \t %s   \t%s점 \t  제목 : %s \t\t내용 : %s\n"
                           ,countSearchResult
                           ,movies.get(Integer.parseInt(post.getMovieSerialNum())-1).getTitle()
                           ,post.getId()
                           ,post.getPublishedDate()
                           ,post.getRating()
                           ,post.getHead()
                           ,post.getBody()
            			   ); 
            
            searchResult.add(post);
            countSearchResult++;
         }
      }
      
      System.out.print("                                       수정할 글 번호 입력 : ");
      
      // 임시 ArrayList에서의 index = 사용자 입력값-1
      int index = Integer.parseInt(scan.nextLine())-1;
      
      // 고유번호를 통해 원본 ArrayList에서의 수정할 post index (index = 글 고유번호 -1)
      int postToEditSerialNum = Integer.parseInt(searchResult.get(index).getPostSerialNum());
      Post originalPost = posts.get(postToEditSerialNum-1);
     
      // 수정할 원본 포스트 삭제
      originalPost.setIsPublic("삭제"); 
      System.out.println("                                       기존 내용 " + originalPost.getIsPublic());
      
      //기존 글에서 영화 정보를 받아 새로 글 작성
      scan.nextLine();
      Post editedpost = new Post();
      editedpost.setMovieSerialNum(originalPost.getMovieSerialNum());
      setOtherThanMovie(editedpost);
      
      posts.add(editedpost);
      
      uiPost.pause(UI.EDIT);
   } 
   
   /**
    * 내가 쓴 포스트 목록
    */
   public void printMyPost() {

	   uiPost.title(UI.PRINT);
      //회원 아이디 받아 글 출력하기
      String id = InitialScreen.getId();
      //TODO 아이디 받아오기
      
      int countSearchResult = 1;
      ArrayList<Post> searchResult = new ArrayList<Post>();
      
      System.out.println("                                       [번호] [영화제목]\t\t[작성자]\t\t[작성일자]\t\t[평점]    [제목]\t\t[내용]");
      // 사용자가 수정할 글을 선택하기위해 필요한 정보만을 출력
      for (Post post : posts)   {
      
         if (post.getId().equals(id)
        		 && post.getIsPublic().equals("노출")) {
         
            System.out.printf("                                        %d.   %s\t%s  \t %s   \t%s점 \t  제목 : %s \t\t내용 : %s\n"
                           ,countSearchResult
                           ,movies.get(Integer.parseInt(post.getMovieSerialNum())-1).getTitle()
                           ,post.getId()
                           ,post.getPublishedDate()
                           ,post.getRating()
                           ,post.getHead()
                           ,post.getBody()
            			   ); 
            
            searchResult.add(post);
            countSearchResult++;
         }
      }
      
      uiPost.pause(UI.PRINT);
   }
   
   /**
    * 포스트 데이터에 저장하기
    */
   public void save() {
         
         try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(POST));
            
            for (Post post : posts) {
               writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s\r\n"
                                 , post.getPostSerialNum()
                                 , post.getMovieSerialNum()
                                 , post.getPublishedDate()
                                 , post.getId()
                                 , post.getHead()
                                 , post.getBody()
                                 , post.getIsPublic()
                                 , post.getRating()));
            }
            
            writer.close();
         } catch (Exception e) {
            System.out.println(e.toString() + "save");
         }
      } 
}