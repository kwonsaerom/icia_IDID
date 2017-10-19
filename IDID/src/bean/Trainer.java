package bean;

public class Trainer {
    private String tId;
    private String tPw;
    private String tName;
    private String tGender;
    private String tBirth;
    private String tDomicile;
    private String tEmail;
    private String tProfilephoto;
    private String tBankname;
    private Long tAccountnum;
    private String tBirth1;
    private String tBirth2;
    private String tBirth3;
    
    public Trainer() {
	}
	public Trainer(String tId, String tPw, String tName, String tGender, String tBirth, String tDomicile,
			String tEmail, String tProfilephoto) {
		this.tId=tId;
		this.tPw=tPw;
		this.tName =tName;
		this.tGender=tGender;
		this.tBirth=tBirth;
		this.tDomicile=tDomicile;
		this.tEmail=tEmail;
		this.tProfilephoto=tProfilephoto;
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String gettPw() {
		return tPw;
	}
	public void settPw(String tPw) {
		this.tPw = tPw;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettGender() {
		return tGender;
	}
	public void settGender(String tGender) {
		this.tGender = tGender;
	}
	public String gettBirth() {
		return tBirth;
	}
	public void settBirth(String tBirth) {
		this.tBirth = tBirth;
	}
	public String gettDomicile() {
		return tDomicile;
	}
	public void settDomicile(String tDomicile) {
		this.tDomicile = tDomicile;
	}
	public String gettEmail() {
		return tEmail;
	}
	public void settEmail(String tEmail) {
		this.tEmail = tEmail;
	}
	public String gettProfilephoto() {
		return tProfilephoto;
	}
	public void settProfilephoto(String tProfilephoto) {
		this.tProfilephoto = tProfilephoto;
	}
	public String gettBankname() {
		return tBankname;
	}
	public void settBankname(String tBankname) {
		this.tBankname = tBankname;
	}
	public Long gettAccountnum() {
		return tAccountnum;
	}
	public void settAccountnum(Long tAccountnum) {
		this.tAccountnum = tAccountnum;
	}
	public String gettBirth1() {
		return tBirth1;
	}
	public void settBirth1(String tBirth1) {
		this.tBirth1 = tBirth1;
	}
	public String gettBirth2() {
		return tBirth2;
	}
	public void settBirth2(String tBirth2) {
		this.tBirth2 = tBirth2;
	}
	public String gettBirth3() {
		return tBirth3;
	}
	public void settBirth3(String tBirth3) {
		this.tBirth3 = tBirth3;
	}
}