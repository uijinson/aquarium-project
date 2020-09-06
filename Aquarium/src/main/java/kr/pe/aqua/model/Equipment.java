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
	private Long equipId; // pk
	@Column(unique = true, nullable = false)
	private String equipName;
	@Column(unique = true, nullable = false)
	private String equipExplain;
	
	private int equipPrice;
	private String equipImg; //상점에 들어갈 사진 
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
