package bean;

public class Program {
	private String p_t_id;//트레이너 아이디
	private String p_t_name;//트레이너 이름
	private String p_name;//프로그램 이름
	private String p_info;//프로그램 소개
	private long p_price;//프로그램 가격
	private int p_code;
	
	public Program(String p_tId, String p_tName, String pName, String pInfo, long pPrice) {
		this.p_t_id = p_tId;
		this.p_t_name = p_tName;
		this.p_name = pName;
		this.p_info = pInfo;
		this.p_price = pPrice;
	}
	public Program() {
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_t_id() {
		return p_t_id;
	}
	public void setP_t_id(String p_t_id) {
		this.p_t_id = p_t_id;
	}
	public String getP_t_name() {
		return p_t_name;
	}
	public void setP_t_name(String p_t_name) {
		this.p_t_name = p_t_name;
	}
	public String getP_info() {
		return p_info;
	}
	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	public long getP_price() {
		return p_price;
	}
	public void setP_price(long p_price) {
		this.p_price = p_price;
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	
}
