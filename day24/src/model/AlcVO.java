package model;

public class AlcVO {
   private int num; // PK
   private String name; // 이름
   private int price; // 가격
   private int cnt; // 재고
   private double abv; // 도수
   private String category; // 카테고리
   
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   public int getCnt() {
      return cnt;
   }
   public void setCnt(int cnt) {
      this.cnt = cnt;
   }
   public double getAbv() {
      return abv;
   }
   public void setAbv(double abv) {
      this.abv = abv;
   }
   public String getCategory() {
      return category;
   }
   public void setCategory(String category) {
      this.category = category;
   }
   @Override
   public String toString() {
      return "AlcVO [num=" + num + ", name=" + name + ", price=" + price + ", cnt=" + cnt + ", abv=" + abv
            + ", category=" + category + "]";
   }
   
}
