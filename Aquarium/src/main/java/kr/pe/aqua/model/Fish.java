package kr.pe.aqua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "FISH_SEQ_GEN", sequenceName="FISH_SEQ", initialValue = 1, allocationSize = 1)
public class Fish {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FISH_SEQ_GEN")
   @Column(name = "fish_id")
   private Long fishId; // pk
   
   @Column(name = "fish_name", unique = true, nullable = false)
   private String fishName;
   
   @Column(name="fish_explain",unique = true, nullable = false)
   private String fishExplain;
   
   @Column(name="fish_price")
   private int fishPrice;
   
   @Column(name="fish_hp")
   private int fishHp;
   
   @Column(name="fish_img")
   private String fishImg; //상점에 들어갈 사진 
   
   @Column(name="fish_src")
   private String  fishSrc; //obj의 경로

   @Builder
   public Fish(Long fishId, String fishName, String fishExplain, int fishPrice, int fishHp, String fishImg,
         String fishSrc) {
      super();
      this.fishId = fishId;
      this.fishName = fishName;
      this.fishExplain = fishExplain;
      this.fishPrice = fishPrice;
      this.fishHp = fishHp;
      this.fishImg = fishImg;
      this.fishSrc = fishSrc;
   }
   

}