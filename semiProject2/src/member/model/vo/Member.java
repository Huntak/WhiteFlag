package member.model.vo;

import java.io.*;
import java.sql.Date;

//vo(value object) == do(domain object) == dto(data transfer object)
   // bean == entity
   // 직렬화할것
   // 모든 필드는 private
   // 기본생성자와 매개변수있는 생성자 필요.
   // 모든 필드에 대한 getter와 setter 구성필요.

public class Member implements Serializable {

   private String MID;
   private String MPWD;
   private String MNAME;
   private String MBIRTH;
   private String MEMAIL;
   private String MPHONE1;
   private String MPHONE2;
   private String MPHONE3;
   private String MADDRESS1;
   private String MADDRESS2;
   private String MADDRESS3;
   private String MADDRESS4;
   private String GRADE;
   private long MTOTALMILEAGE;
   private long MTOTALPURCHASE;
   private char MGENDER;
   private Date MENROLLDATE;
      
   
   public Member(){}

   public Member(String mID, String mPWD, String mEMAIL, String mPHONE1, String mPHONE2, String mPHONE3, String mADDRESS1, String mADDRESS2,
			String mADDRESS3, String mADDRESS4){
	   super();
	      MID = mID;
	      MPWD = mPWD;
	      MEMAIL = mEMAIL;
	      MPHONE1 = mPHONE1;
	      MPHONE2 = mPHONE2;
	      MPHONE3 = mPHONE3;
	      MADDRESS1 = mADDRESS1;
	      MADDRESS2 = mADDRESS2;
	      MADDRESS3 = mADDRESS3;
	      MADDRESS4 = mADDRESS4;
   }

   public Member(String mID, String mPWD, String mNAME, String mBIRTH, String mEMAIL, String mPHONE1, String mPHONE2,String mPHONE3,String mADDRESS1,
         String mADDRESS2, String mADDRESS3, String mADDRESS4, String gRADE, long mTOTALMILEAGE, long mTOTALPURCHASE,
         char mGENDER, Date mENROLLDATE) {
      super();
      MID = mID;
      MPWD = mPWD;
      MNAME = mNAME;
      MBIRTH = mBIRTH;
      MEMAIL = mEMAIL;
      MPHONE1 = mPHONE1;
      MPHONE2 = mPHONE2;
      MPHONE3 = mPHONE3;
      MADDRESS1 = mADDRESS1;
      MADDRESS2 = mADDRESS2;
      MADDRESS3 = mADDRESS3;
      MADDRESS4 = mADDRESS4;
      GRADE = gRADE;
      MTOTALMILEAGE = mTOTALMILEAGE;
      MTOTALPURCHASE = mTOTALPURCHASE;
      MGENDER = mGENDER;
      MENROLLDATE = mENROLLDATE;
   }


   public String getMID() {
      return MID;
   }


   public void setMID(String mID) {
      MID = mID;
   }


   public String getMPWD() {
      return MPWD;
   }


   public void setMPWD(String mPWD) {
      MPWD = mPWD;
   }


   public String getMNAME() {
      return MNAME;
   }


   public void setMNAME(String mNAME) {
      MNAME = mNAME;
   }


   public String getMBIRTH() {
      return MBIRTH;
   }


   public void setMBIRTH(String mBIRTH) {
      MBIRTH = mBIRTH;
   }


   public String getMEMAIL() {
      return MEMAIL;
   }


   public void setMEMAIL(String mEMAIL) {
      MEMAIL = mEMAIL;
   }


   public String getMPHONE1() {
      return MPHONE1;
   }


   public void setMPHONE1(String mPHONE1) {
      MPHONE1 = mPHONE1;
   }
   
   public String getMPHONE2() {
      return MPHONE2;
   }


   public void setMPHONE2(String mPHONE2) {
      MPHONE2 = mPHONE2;
   }
   
   public String getMPHONE3() {
      return MPHONE3;
   }


   public void setMPHONE3(String mPHONE3) {
      MPHONE3 = mPHONE3;
   }


   public String getMADDRESS1() {
      return MADDRESS1;
   }


   public void setMADDRESS1(String mADDRESS1) {
      MADDRESS1 = mADDRESS1;
   }


   public String getMADDRESS2() {
      return MADDRESS2;
   }


   public void setMADDRESS2(String mADDRESS2) {
      MADDRESS2 = mADDRESS2;
   }


   public String getMADDRESS3() {
      return MADDRESS3;
   }


   public void setMADDRESS3(String mADDRESS3) {
      MADDRESS3 = mADDRESS3;
   }


   public String getMADDRESS4() {
      return MADDRESS4;
   }


   public void setMADDRESS4(String mADDRESS4) {
      MADDRESS4 = mADDRESS4;
   }


   public String getGRADE() {
      return GRADE;
   }


   public void setGRADE(String gRADE) {
      GRADE = gRADE;
   }


   public long getMTOTALMILEAGE() {
      return MTOTALMILEAGE;
   }


   public void setMTOTALMILEAGE(long mTOTALMILEAGE) {
      MTOTALMILEAGE = mTOTALMILEAGE;
   }


   public long getMTOTALPURCHASE() {
      return MTOTALPURCHASE;
   }


   public void setMTOTALPURCHASE(long mTOTALPURCHASE) {
      MTOTALPURCHASE = mTOTALPURCHASE;
   }


   public char getMGENDER() {
      return MGENDER;
   }


   public void setMGENDER(char mGENDER) {
      MGENDER = mGENDER;
   }


   public Date getMENROLLDATE() {
      return MENROLLDATE;
   }


   public void setMENROLLDATE(Date mENROLLDATE) {
      MENROLLDATE = mENROLLDATE;
   }


   @Override
   public String toString() {
      return  MID + ", " + MPWD + ", " + MNAME + ", " + MBIRTH + ", "
            + MEMAIL + ", " + MPHONE1 + ", " + MPHONE2 + ", " + MPHONE3 + ", " +   MADDRESS1 + ", " + MADDRESS2
            + ", " + MADDRESS3 + ", " + MADDRESS4 + ", " + GRADE + ", "
            + MTOTALMILEAGE + ", " + MTOTALPURCHASE + ", " + MGENDER + ", "
            + MENROLLDATE ;
   }

   
   
   
}