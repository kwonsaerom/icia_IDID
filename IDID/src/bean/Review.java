package bean;

public class Review {
	private String r_m_id;
	private String r_m_name;
	private String r_title;
	private String r_substance;
	private String r_fileName;
	private String r_date;
	private int r_group;
	private int r_code; //글번호
	private int r_count;//조회수
	
	public Review(String m_id, String r_title, String r_substance, String r_fileName) {
		this.r_m_id=m_id;
		this.r_title = r_title;
		this.r_substance = r_substance;
		this.r_fileName = r_fileName;
	}
	public Review() {}
	
	public Review(int code, String modifyTitle, String modifySubstance, String modifyFilename) {
		this.r_code = code;
		this.r_title = modifyTitle;
		this.r_substance = modifySubstance;
		this.r_fileName = modifyFilename;
	}
	public String getR_m_id() {
		return r_m_id;
	}
	public void setR_m_id(String r_m_id) {
		this.r_m_id = r_m_id;
	}
	public String getR_m_name() {
		return r_m_name;
	}
	public void setR_m_name(String r_m_name) {
		this.r_m_name = r_m_name;
	}
	public String getR_title() {
		return r_title;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	public String getR_substance() {
		return r_substance;
	}
	public void setR_substance(String r_substance) {
		this.r_substance = r_substance;
	}
	public String getR_fileName() {
		return r_fileName;
	}
	public void setR_fileName(String r_fileName) {
		this.r_fileName = r_fileName;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public int getR_group() {
		return r_group;
	}
	public void setR_group(int r_group) {
		this.r_group = r_group;
	}
	public int getR_code() {
		return r_code;
	}
	public void setR_code(int r_code) {
		this.r_code = r_code;
	}
	public int getR_count() {
		return r_count;
	}
	public void setR_count(int r_count) {
		this.r_count = r_count;
	}
	
}
