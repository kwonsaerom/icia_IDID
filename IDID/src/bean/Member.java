package bean;

public class Member {
   private String mId;
    private String mPw;
    private String mName;
    private String mGender;
    private String mBirth;
    private String mDomicile;
    private String mEmail;
    private int mBheight;
    private int mBweight;
    private String mWish;
    private String mbirth1;
    private String mbirth2;
    private String mbirth3;
    
    
    @Override
    public String toString() {
       return mId.toString();
    }
    
    

   public String getMbirth1() {
      return mbirth1;
   }



   public void setMbirth1(String mbirth1) {
      this.mbirth1 = mbirth1;
   }



   public String getMbirth2() {
      return mbirth2;
   }



   public void setMbirth2(String mbirth2) {
      this.mbirth2 = mbirth2;
   }



   public String getMbirth3() {
      return mbirth3;
   }



   public void setMbirth3(String mbirth3) {
      this.mbirth3 = mbirth3;
   }



   public Member(String m_id) {
       
    }
    public Member() {
       
    }
   public Member(String mId, String mPw, String mName, String mGender, String mBirth, String mDomicile, String mEmail,
         int mBheight, int mBweight, String mWish) {
      this.mId = mId;
      this.mPw = mPw;
      this.mName = mName;
      this.mGender = mGender;
      this.mBirth = mBirth;
      this.mDomicile = mDomicile;
      this.mEmail = mEmail;
      this.mBheight = mBheight;
      this.mBweight = mBweight;
      this.mWish = mWish;
   }
   public String getmId() {
      return mId;
   }
   public void setmId(String mId) {
      this.mId = mId;
   }
   public String getmPw() {
      return mPw;
   }
   public void setmPw(String mPw) {
      this.mPw = mPw;
   }
   public String getmName() {
      return mName;
   }
   public void setmName(String mName) {
      this.mName = mName;
   }
   public String getmGender() {
      return mGender;
   }
   public void setmGender(String mGender) {
      this.mGender = mGender;
   }
   public String getmBirth() {
      return mBirth;
   }
   public void setmBirth(String mBirth) {
      this.mBirth = mBirth;
   }
   public String getmDomicile() {
      return mDomicile;
   }
   public void setmDomicile(String mDomicile) {
      this.mDomicile = mDomicile;
   }
   public String getmEmail() {
      return mEmail;
   }
   public void setmEmail(String mEmail) {
      this.mEmail = mEmail;
   }
   public int getmBheight() {
      return mBheight;
   }
   public void setmBheight(int mBheight) {
      this.mBheight = mBheight;
   }
   public int getmBweight() {
      return mBweight;
   }
   public void setmBweight(int mBweight) {
      this.mBweight = mBweight;
   }
   public String getmWish() {
      return mWish;
   }
   public void setmWish(String mWish) {
      this.mWish = mWish;
   }
    
    
}