package bean;

public class Reply {
	private int RP_NUM;
	private String RP_ID;
	private String RP_DATE;
	private String RP_COMMENT;
	private int RP_D_CODE;
	
	public Reply() {
	}
	public Reply(int rP_NUM, String rP_ID, String rP_DATE, String rP_COMMENT, int rP_D_CODE) {
		RP_NUM = rP_NUM;
		RP_ID = rP_ID;
		RP_DATE = rP_DATE;
		RP_COMMENT = rP_COMMENT;
		RP_D_CODE = rP_D_CODE;
	}

	public Reply(String m_id, String rP_COMMENT, int rP_D_CODE) {
		this.RP_ID = m_id;
		this.RP_COMMENT = rP_COMMENT;
		this.RP_D_CODE = rP_D_CODE;
	}

	public int getRP_NUM() {
		return RP_NUM;
	}
		
	public String getRP_ID() {
		return RP_ID;
	}

	public void setRP_ID(String rP_M_ID) {
		RP_ID = rP_M_ID;
	}


	public void setRP_NUM(int rP_NUM) {
		RP_NUM = rP_NUM;
	}
	public String getRP_DATE() {
		return RP_DATE;
	}
	public void setRP_DATE(String rP_DATE) {
		RP_DATE = rP_DATE;
	}
	public String getRP_COMMENT() {
		return RP_COMMENT;
	}
	public void setRP_COMMENT(String rP_COMMENT) {
		RP_COMMENT = rP_COMMENT;
	}
	public int getRP_D_CODE() {
		return RP_D_CODE;
	}
	public void setRP_D_CODE(int rP_D_CODE) {
		RP_D_CODE = rP_D_CODE;
	}
	
	
}
