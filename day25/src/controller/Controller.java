package controller;

import java.util.ArrayList;

import model.BookDAO;
import model.BookVO;
import model.CgvDAO;
import model.CgvVO;
import model.Crawling;
import model.MemberDAO;
import model.MemberVO;
import view.View;

public class Controller {
   MemberVO mvo;
   MemberDAO mDAO;
   CgvDAO cDAO;
   View view;
   CgvVO vo;
   BookVO dvo;
   BookDAO bDAO;

   public Controller() {
      mDAO = new MemberDAO();
      cDAO = new CgvDAO();
      view = new View();
      vo = new CgvVO();
      if (!(cDAO.hasSample(vo))) {
         Crawling.sample();
      } else {
    	  
      }
   }

   public void startApp() {
      ArrayList<CgvVO> datas = new ArrayList<CgvVO>();
      bDAO = new BookDAO();
      while (true) {
         view.loginView();
         if (View.action == 1) { // 로그인
            MemberVO vo = new MemberVO();
            vo = view.login(); // vo에 view.login()에서 받은 id와 pw을 담는다.
            MemberVO data = mDAO.login(vo); // MemberDAO의 login메서드로 해당 회원의 정보를 return
            // data = mDAO.selectOne(vo);
            if (data == null) { // 그에 맞는 객체가 존재하지 않으면 로그인 실패
               continue;
            }
            view.logInT();

            while (true) {
               data = mDAO.selectOne(vo);
               if (data == null) { // 회원 탈퇴 후 name을 갖지 않는다면 초기화면으로 돌아간다.
                  break;
               }
               view.startView();
               if (View.action == 1) { // 영화 목록
                  CgvVO cvo = new CgvVO();
                  datas = cDAO.selectAll(cvo);
                  view.movieList(datas); // 전체 영화 목록을 출력해주는 selectAll() & movieList() 메서드
                  int num = 0;
                  while (true) {
                     if (view.ticket()) {
                        if (datas.size() == 0) { // 검색결과가 없을시(datas.size()==0) 문구 출력 후 while문을 빠져나간다.
                           view.movieX();
                           break;
                        }
                        view.mSelect(); // 예매할 영화번호 문구 출력
                        num = view.inputInt(); // 예매할 영화 번호를 받는다.
                        CgvVO cvo1 = new CgvVO();
                        if (num > datas.size() || num < 1) { // 범위에 따른 유효성 검사
                           System.out.println("범위외 입력입니다.");
                           continue;
                        }
                        cvo1.setCid(datas.get(num - 1).getCid()); // 검색 결과가 담긴 리스트에서 사용자의 입력값-1에 해당하는 영화의 cid를
                                                         // 객체에 저장
                        cvo1.setBookcnt(1);// 예매를 계속 진행할 것인지에 대해 묻는 메서드
                        if (cDAO.update(cvo1)) { // 해당 영화의 예매 횟수에 대해 update 진행
//                           data.setMbook(datas.get(num - 1).getTitle()); // 로그인한 사람의 최근예약영화에 예매 영화의 제목을 저장한다.
//                           data.setMid(data.getMid());
                           BookVO bvo1 = new BookVO();
                           bvo1.setMid(data.getMid());
                           // System.out.println(data.getMid());
                           bvo1.setCid(datas.get(num - 1).getCid());
                           // System.out.println(datas.get(num - 1).getCid());
                           if (bDAO.insert(bvo1)) {
                              System.out.println("예매완료");
                              break;
                           }
                           System.out.println("예매실패");
                        }
                        System.out.println("예매실패");
                     } else {
                        break;
                     }
                     continue;
                  }
               } else if (View.action == 2) { // 영화 검색
                  CgvVO cvo = new CgvVO();

                  cvo = view.searchView(); // view의 serachView() 메서드를 사용해 검색하고싶은 영화 제목과 장르를 받는다.
                  datas = cDAO.selectAll(cvo); // 해당 검색결과를 ArrayList에 담는다.
                  view.movieList(datas);
                  int num = 0;
                  while (true) {
                	  if (datas.size() == 0) { // 검색결과가 없을시(datas.size()==0) 문구 출력 후 while문을 빠져나간다.
                		  view.movieX();
                		  break;
                	  }
                     if (view.ticket()) {
                        view.mSelect(); // 예매할 영화번호 문구 출력
                        num = view.inputInt(); // 예매할 영화 번호를 받는다.
                        CgvVO cvo1 = new CgvVO();
                        if (num > datas.size() || num < 1) { // 범위에 따른 유효성 검사
                           System.out.println("범위외 입력입니다.");
                           continue;
                        }
                        cvo1.setCid(datas.get(num - 1).getCid()); // 검색 결과가 담긴 리스트에서 사용자의 입력값-1에 해당하는 영화의 cid를
                                                         // 객체에 저장
                        cvo1.setBookcnt(1);// 예매를 계속 진행할 것인지에 대해 묻는 메서드
                        if (cDAO.update(cvo1)) { // 해당 영화의 예매 횟수에 대해 update 진행
                           BookVO bvo1 = new BookVO();
                           bvo1.setMid(data.getMid());
                           bvo1.setCid(datas.get(num - 1).getCid());
                           if (bDAO.insert(bvo1)) {
                              System.out.println("예매완료");
                              break;
                           }
                           System.out.println("예매실패");
                        }
                        System.out.println("예매실패");
                     } else {
                        break;
                     }
                  }
               } else if (View.action == 3) {
                  while (true) {
                     view.myPageView();
                     view.inputInt();
                     if (View.i == 1) { // 마이페이지
                        BookVO bvo = new BookVO();
                        bvo.setMid(data.getMid());
                        ArrayList<BookVO> bdatas = new ArrayList<BookVO>();
                        bdatas = bDAO.selectAll(bvo);
                        view.myPage(data); // 로그인 객체에 담긴 정보를 모두 보여준다.
                        view.cancle(bdatas);
                        continue;
                     } else if (View.i == 2) { // 회원 탈퇴
                        boolean ans = view.deleteMember(); // 삭제 여부에 대한 물음을 String ans에 담는다.
                        if (ans) {
                           MemberVO data2 = view.login(); // view.login()을 통해 삭제하는 계정의 ID와 PW를 확인
                           if (data2.getMid().equals(data.getMid())) {
                              if (data2.getMpw().equals(data.getMpw())) {
                                 BookVO vo3 = new BookVO();
                                 vo3.setMid(data.getMid());
                                 ArrayList<BookVO> datas2 = bDAO.selectAll(vo3);
                                 int i = 0;
                                 while (i < datas2.size()) {
                                    CgvVO vo4 = new CgvVO();// 예매내역 정보 삭제 내용추가
                                    vo4.setCid(datas2.get(i).getCid());
                                    vo4.setBookcnt(-1); // 영화 cnt 감소
                                    cDAO.update(vo4);
                                    i++;
                                 }
                                 bDAO.delete(vo3);
                                 mDAO.delete(data);
                                 view.deleteT(); // 계정 삭제 후 while문을 빠져나간다.
                                 break;
                              }
                              view.mpwF(); // 비밀번호 x
                              continue;
                           }
                           view.midF(); // 아이디 x
                           continue;
                        }
                        view.deleteF();
                     } else if (View.i == 3) { // 예매취소
                        BookVO bvo = new BookVO(); // 멤버의 값을 담을 객체 임시생성
                        bvo.setMid(data.getMid()); // 내정보를 조회한 멤버의 데이터 저장
                        ArrayList<BookVO> datas2 = bDAO.selectAll(bvo); // 그 멤버의 데이터를 selectAll로 조회 후 datas1에 담음
                        if(datas2.size()==0) {
                        	view.nomovie();
                        	continue;
                        }
                        view.cancle(datas2); // 예메 취소 문구와 그 정보 출력
                        view.cancleNum(); // 취소할 영화 선택
                        int i = view.inputInt(); // 입력값 저장
                        if (view.mcancle()) { // 취소 완료
                           BookVO bvo2 = new BookVO();
                           bvo2.setBid(datas2.get(i - 1).getBid());
                           bDAO.delete(bvo2);
                           CgvVO vo2 = new CgvVO();
                           vo2.setCid(datas2.get(i - 1).getCid());
                           vo2.setBookcnt(-1);
                           cDAO.update(vo2);
                        }
                        continue;
                     } else if (View.i == 4) {
                        view.backpage(); // 이전 페이지
                        break;
                     }
                  }
               } else if (View.action == 4) { // 로그아웃
                  view.logOutS();
                  break;
               }
            }
         } else if (View.action == 2) { // 회원가입
            MemberVO vo = new MemberVO();
            while (true) {
               vo = view.signUpId(); // 회원가입에 필요한 정보를 vo 객체에 담는다.
               if (mDAO.hasSame(vo)) { // 중복검사
                  view.idOverLap();
                  view.signF();
                  continue;
               } else {
                  MemberVO vo2 = new MemberVO();
                  vo2 = view.signUpPw();
                  vo.setMpw(vo2.getMpw());
                  vo.setMname(vo2.getMname());
                  mDAO.user(vo);
                  view.signT();
                  break;
               }
            }
         } else if (View.action == 3) { // 프로그램 종료
            break;
         }
      }
   }
}