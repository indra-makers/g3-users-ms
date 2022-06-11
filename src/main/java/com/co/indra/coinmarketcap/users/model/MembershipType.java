package com.co.indra.coinmarketcap.users.model;

import java.io.Serializable;

public class MembershipType implements Serializable {
   private Long membershipId;
   private String type;

   public MembershipType(Long membershipId, String type) {
      this.membershipId = membershipId;
      this.type = type;
   }

   public MembershipType() {
   }

   public Long getMembershipId() {
      return membershipId;
   }

   public void setMembershipId(Long membershipId) {
      this.membershipId = membershipId;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

}