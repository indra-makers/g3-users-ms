package com.co.indra.coinmarketcap.users.messaging;

import java.io.Serializable;


public class SenderData implements Serializable{
   
   
   private int userId;
   private String mail;
   private String phone;
   
   
   
   public SenderData(int userId, String mail, String phone) {
      super();
      this.userId = userId;
      this.mail = mail;
      this.phone = phone;
   }


   public SenderData() {
      super();
   }


   public int getUserId() {
      return userId;
   }


   public void setUserId(int userId) {
      this.userId = userId;
   }


   public String getMail() {
      return mail;
   }


   public void setMail(String mail) {
      this.mail = mail;
   }


   public String getPhone() {
      return phone;
   }


   public void setPhone(String phone) {
      this.phone = phone;
   }
   
   
   
   

}
