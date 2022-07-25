package model;

import java.util.ArrayList;

public class AlcDAO {
   ArrayList<AlcVO> datas = new ArrayList<AlcVO>(); // 전체배열
   ArrayList<CategoryVO> category = new ArrayList<CategoryVO>(); // 주종배열(부분배열)
   int pk = 101; // Primary Key
   int cpk = 1; // 카테고리에 대한 pk , ex) 소주는 1 , 맥주는 2 , 막걸리 3

   AlcVO vo1 = new AlcVO();
   {
      vo1.setName("참이슬");
      vo1.setPrice(2000);
      vo1.setCnt(10);
      vo1.setCategory("소주");
      vo1.setAbv(16.9);
      vo1.setNum(pk++);
      datas.add(vo1);
   }
   AlcVO vo2 = new AlcVO();
   {
      vo2.setName("처음처럼");
      vo2.setPrice(2000);
      vo2.setCnt(20);
      vo2.setCategory("소주");
      vo2.setAbv(16.0);
      vo2.setNum(pk++);
      datas.add(vo2);
   }
   AlcVO vo3 = new AlcVO();
   {
      vo3.setName("시원");
      vo3.setPrice(2000);
      vo3.setCnt(10);
      vo3.setCategory("소주");
      vo3.setAbv(16.9);
      vo3.setNum(pk++);
      datas.add(vo3);
   }
   AlcVO vo4 = new AlcVO();
   {
      vo4.setName("테라");
      vo4.setPrice(3000);
      vo4.setCnt(10);
      vo4.setCategory("맥주");
      vo4.setAbv(5.6);
      vo4.setNum(pk++);
      datas.add(vo4);
   }
   AlcVO vo5 = new AlcVO();
   {
      vo5.setName("카스");
      vo5.setPrice(3000);
      vo5.setCnt(15);
      vo5.setCategory("맥주");
      vo5.setAbv(5.5);
      vo5.setNum(pk++);
      datas.add(vo5);
   }
   AlcVO vo6 = new AlcVO();
   {
      vo6.setName("하이트");
      vo6.setPrice(3000);
      vo6.setCnt(20);
      vo6.setCategory("맥주");
      vo6.setAbv(5.8);
      vo6.setNum(pk++);
      datas.add(vo6);
   }
   AlcVO vo7 = new AlcVO();
   {
      vo7.setName("원막걸리");
      vo7.setPrice(3000);
      vo7.setCnt(10);
      vo7.setCategory("막걸리");
      vo7.setAbv(6.0);
      vo7.setNum(pk++);
      datas.add(vo7);
   }
   AlcVO vo8 = new AlcVO();
   {
      vo8.setName("장수막걸리");
      vo8.setPrice(3000);
      vo8.setCnt(10);
      vo8.setCategory("막걸리");
      vo8.setAbv(6.5);
      vo8.setNum(pk++);
      datas.add(vo8);
   }
   AlcVO vo9 = new AlcVO();
   {
      vo9.setName("밤막걸리");
      vo9.setPrice(3000);
      vo9.setCnt(10);
      vo9.setCategory("막걸리");
      vo9.setAbv(9.6);
      vo9.setNum(pk++);
      datas.add(vo9);
   }
   CategoryVO cvo1 = new CategoryVO();
   {
      cvo1.setName("소주");
      cvo1.setNum(cpk++);
      category.add(cvo1);
   }
   CategoryVO cvo2 = new CategoryVO();
   {
      cvo2.setName("맥주");
      cvo2.setNum(cpk++);
      category.add(cvo2);
   }
   CategoryVO cvo3 = new CategoryVO();
   {
      cvo3.setName("막걸리");
      cvo3.setNum(cpk++);
      category.add(cvo3);
   }


   public ArrayList<AlcVO> selectAll(AlcVO vo) { // AlcVO에 있는 값들 전체 리턴
      if(vo.getName()!=null) {
         // 검색 
         ArrayList<AlcVO> datas=new ArrayList<AlcVO>(); 
         for(int i=0;i<this.datas.size();i++) { // datas의 크기만큼 반복해서 
            if(this.datas.get(i).getName().equals(vo.getName())) { // 인자로 들어온 vo의 이름이 같다면
               datas.add(this.datas.get(i)); // 같은 그 객체를 datas에 넣는다.
            }
         }
         return datas;
      }
      return datas;
   }
   public AlcVO selectOne(AlcVO vo) { // AlcVO에서 들어온 값을 비교하여 리턴
      for (AlcVO data : datas) {
         if (data.getName().equals(vo.getName())) { // 이름으로 비교해서 해당 객체 리턴
            return data;
         }
      }
      return null;
   }

   public ArrayList<CategoryVO> categoryAll(CategoryVO cate) { // category(대분류) 배열리턴
      return category;
   }

   public CategoryVO categoryOne(CategoryVO cate) { // category의 selectOne
      for (CategoryVO data : category) { // data라는 객체에 category의 값을 넣는다
         if (data.getName().equals(cate.getName())) { // 만약 들어온 이름과 data의 이름이 같다면
            return data; // data를 리턴한다.
         }
      }
      return null;
   }

   public boolean update(AlcVO vo) { // cnt 값 수정을 위한 메서드
      for (AlcVO data : datas) {
         if (data.getName().equals(vo.getName())) { // 만약 이름이 같다면 > 구매자에서 사용
            if (data.getCnt() - vo.getCnt() < 0) { // 유효성검사 기존cnt보다 들어온cnt는 높을 수 없다
               return false; // 실패
            } else {
               data.setCnt(data.getCnt() - vo.getCnt()); // vo 객체에 들어온 cnt 값을 기존에 cnt에서 빼준다.
            }
         }
         if (data.getNum()==vo.getNum()) { // 만약 pk 값이 같다면 > 관리자에서 사용
            if (data.getCnt() - vo.getCnt() < 0) { // 유효성검사 기존cnt보다 들어온cnt는 높을 수 없다
               return false; // 실패
            } else {
               data.setCnt(data.getCnt() - vo.getCnt()); // vo 객체에 들어온 cnt 값을 기존에 cnt에서 빼준다.
            }
         }
      }
      return true;
   }

   public boolean insert(AlcVO vo) { // 추가 메서드
      try {
         vo.setNum(pk++); // 재고가 추가됨에 따라 pk 매핑
         datas.add(vo); // 받아온 객체에 대한 정보를 datas에 추가
         return true; // 재고 추가 성공
      } catch (Exception e) {
         return false;
      }

   }

   public boolean delete(AlcVO vo) { // 제품 삭제 메서드
      for (int i = 0; i < datas.size(); i++) {
         if(datas.get(i).getNum() == vo.getNum()) { // 객체로 들어온 vo의 pk 가 기존 데이터의 pk와 같다면
            datas.remove(i); // 해당 객체 삭제
            return true;
         } 
      }
      return false;
   }

}