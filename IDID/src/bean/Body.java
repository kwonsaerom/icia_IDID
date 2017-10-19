package bean;

public class Body {
   private String b_code; //신체정보 코드
   private String b_m_id; //회원 아이디
   private String b_t_id; //트레이너 아이디
   private String b_date; //작성날짜
   private int b_weight; //체중
   private int b_height; //신장
   
   
   public Body(String m_id, String t_id, String date, int height, int weight) {
      b_m_id=m_id;
      b_t_id=t_id;
      b_date=date;
      b_height=height;
      b_weight=weight;
   }
   
   
   public String getB_code() {
      return b_code;
   }
   public void setB_code(String b_code) {
      this.b_code = b_code;
   }
   public String getB_m_id() {
      return b_m_id;
   }
   public void setB_m_id(String b_m_id) {
      this.b_m_id = b_m_id;
   }
   public String getB_t_id() {
      return b_t_id;
   }
   public void setB_t_id(String b_t_id) {
      this.b_t_id = b_t_id;
   }
   public String getB_date() {
      return b_date;
   }
   public void setB_date(String b_date) {
      this.b_date = b_date;
   }
   public int getB_weight() {
      return b_weight;
   }
   public void setB_weight(int b_weight) {
      this.b_weight = b_weight;
   }
   public int getB_height() {
      return b_height;
   }
   public void setB_height(int b_height) {
      this.b_height = b_height;
   }
   
   
}