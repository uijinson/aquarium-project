package kr.pe.aqua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(name = "EQUIP_SEQ_GEN",sequenceName="EQUIP_SEQ", initialValue = 1, allocationSize = 1)
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EQUIP_SEQ_GEN")
	@Column(name="equip_id")
	private Long equipId; // pk
	
	@Column(name="equip_name", unique = true, nullable = false)
	private String equipName;
	
	@Column(name="equip_explain", unique = true, nullable = false)
	private String equipExplain;
	
	@Column(name="equip_price")
	private int equipPrice;
	
	@Column(name="equip_img")
	private String equipImg; //상점에 들어갈 사진 
	
	@Column(name="equip_src")
	private String  equipSrc; //obj의 경로
	
	@Builder
	public Equipment(Long equipId, String equipName, String equipExplain, int equipPrice, String equipImg,
			String equipSrc) {
		super();
		this.equipId = equipId;
		this.equipName = equipName;
		this.equipExplain = equipExplain;
		this.equipPrice = equipPrice;
		this.equipImg = equipImg;
		this.equipSrc = equipSrc;
	}

}
