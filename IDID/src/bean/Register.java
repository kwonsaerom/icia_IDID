package bean;

public class Register {
   private String rg_m_id;
   private String rg_t_id;
   private String rg_m_name;
   private String rg_t_name;
   private int rg_p_code;
   
   
   public Register(String m_id, String t_id, String m_name, String t_name, int p_code) {
	   rg_m_id=m_id;
	   rg_t_id=t_id;
	   rg_m_name=m_name;
	   rg_t_name=t_name;
	   rg_p_code=p_code;
   }

   public Register() {
	// TODO Auto-generated constructor stub
}

public String getRg_m_id() {
      return rg_m_id;
   }
   public void setRg_m_id(String rg_m_id) {
      this.rg_m_id = rg_m_id;
   }
   public String getRg_t_id() {
      return rg_t_id;
   }
   public void setRg_t_id(String rg_t_id) {
      this.rg_t_id = rg_t_id;
   }
   public String getRg_m_name() {
      return rg_m_name;
   }
   public void setRg_m_name(String rg_m_name) {
      this.rg_m_name = rg_m_name;
   }
   public String getRg_t_name() {
      return rg_t_name;
   }
   public void setRg_t_name(String rg_t_name) {
      this.rg_t_name = rg_t_name;
   }
   public int getRg_p_code() {
      return rg_p_code;
   }
   public void setRg_p_code(int rg_p_code) {
      this.rg_p_code = rg_p_code;
   }
   
}