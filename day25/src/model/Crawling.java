package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
   //public static void crawling() {
      public static void sample() {
         
      String cgvUrl = "http://www.cgv.co.kr/movies/?lt=1&ft=0"; // 크롤링하려는 페이지의 url
      Document doc = null; // Document 객체 생성
      URL url = null;   // URL 객체생성
      InputStream in = null; // InputStream 객체 생성
      OutputStream out = null; // OutputStream 객체 생성
      
      try {
         doc = Jsoup.connect(cgvUrl).get(); // Jsoup 클래스로 url 연결하여 정보를 doc에 담음

      } catch (IOException e) {
         e.printStackTrace();
      }

      String title = "strong.title"; // 영화제목
      Elements eles1 = doc.select(title); // 해당 url에서 영화 제목 정보만 eles1에 담음

      String img = ".thumb-image > img"; // 이미지
      Elements eles2 = doc.select(img); // 해당 url에서 영화 이미지 정보만 eles2에 담음

      String category = "div.box-image>a"; // 장르
      Elements eles3 = doc.select(category); // 해당 url에서 영화 장르를 추출하기위한 정보를 eles3에 담음
                                    // 장르 정보가 완전히 정제되어 있지 않음
                                    // 영화 상세정보 페이지의 주소가 있는 a태그

      Iterator<Element> itr1 = eles1.iterator(); // 영화제목 정보를 요소 별로 분리 
      Iterator<Element> itr2 = eles2.iterator(); // 이미지 정보를 요소 별로 분리
      Iterator<Element> itr3 = eles3.iterator(); // 장르 정보를 요소 별로 분리



      int N = 1; // png 파일에 붙을 숫자
      int num = 0;
      while(itr3.hasNext()) { // itr3의 정보를 모두 나타낼때까지 반복
         try {
            CgvVO vo = new CgvVO();
            CgvDAO cDAO = new CgvDAO();            

            String img2 = itr2.next().attr("src"); // 영화 이미지 정보의 속성값인 src(이미지 주소)를 담음
            url = new URL(img2); // url 객체에 이미지 주소를 담음, 열림 1
            in = url.openStream(); // in 객체에 url 정보 담음(받고싶은 데이터 연결)
            out = new FileOutputStream("C:\\0607min\\img\\"+N+".png"); // out 객체에 저장경로(저장을 원하는 위치) 입력
            N++;
            while(true) {
               int data = in.read(); // in 객체로 해당 이미지를 읽어들임

               if(data==-1) { // 더이상 읽을것이 없으면 멈춤
                  break;
               } 
               out.write(data); // 읽어들인 데이터를 경로에 작성
            }
            in.close(); // 저장이 끝난후 사용한 객체는 close, 닫음 1
            out.close();

            String title2 = itr1.next().text(); // 태그를 제외한 영화 제목 정보를 담음
            String category2 = itr3.next().attr("href"); // 장르를 정제하기 위해 속성값 href(상세정보 페이지) 추출
            System.out.println(category2);
            String str = "https://www.cgv.co.kr/movies/detail-view/?"+category2.substring(21,category2.length());
            // 추출한 주소에서 다시 한번 원하는 영화의 midindex 추출 (개별 영화의 상세정보 페이지)

            String cgvUrl2 = str; // 개별 영화의 상세정보 페이지
            Document doc2 = null;
            URL url2 = null;
            InputStream in2 = null;
            OutputStream out2 = null;
            try {
               doc2 = Jsoup.connect(cgvUrl2).get(); // 상세정보 페이지에 연결하여 정보를 담음

            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            String category3 = "div>dl>dt"; // 감독, 배우, 장르 등등
            Elements eles4 = doc2.select(category3); // 영화소개정보(장르포함)만 eles4에 담음 
            Iterator<Element> itr4 = eles4.iterator(); // 영화정보를 요소별로 분리
            while(itr4.hasNext()) { // itr4의 요소를 모두 나타낼때 까지 반복
               String category4 = itr4.next().text(); // 태그를 제외한 영화정보

               String a = "장르 : ";
               int c = category4.indexOf(a); // 장르만 추출해내기 위해 사용(장르가 아니면 -1 반환)            
               if(c!=-1) { // 장르 정보만 추출하여 객체에 입력
                  System.out.println(title2);
                  System.out.println(img2);
                  System.out.println(category4.substring(5, category4.length()));
                  System.out.println();
                  vo.setGenre(category4.substring(5, category4.length()));;
               }
            }
            if(vo.getGenre()==null) {
            	continue;
            }
            vo.setTitle(title2); // 객체에 영화제목 입력
            vo.setImage(img2); // 이미지 입력
            vo.setBookcnt(num); // 예매횟수 기본값 설정
            cDAO.insert(vo); // DAO의 insert 함수로 DB에 영화정보 추가
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } finally {
            try {
               in.close();
               out.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }            
         }
      }
   }
}