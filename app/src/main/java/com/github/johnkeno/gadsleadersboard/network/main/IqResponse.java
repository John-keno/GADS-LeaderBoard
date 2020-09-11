package com.github.johnkeno.gadsleadersboard.network.main;

public class IqResponse {

   private String name;
   private int score;
   private String country;
   private String badgeUrl;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getBadgeUrl() {
      return badgeUrl;
   }

   public void setBadgeUrl(String badgeUrl) {
      this.badgeUrl = badgeUrl;
   }

   @Override
   public String toString() {
      return  score + " skill IQ score, " + country;
   }
}
