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
@SequenceGenerator(name = "ACC_SEQ_GEN",sequenceName="ACC_SEQ", initialValue = 1, allocationSize = 1)
public class Accessory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_SEQ_GEN")
	@Column(name="acc_id")
	private Long accId; // pk
	
	@Column(name="acc_name", unique = true, nullable = false)
	private String accName;
	
	@Column(name="acc_explain", unique = true, nullable = false)
	private String accExplain;
	
	@Column(name="acc_price")
	private int accPrice;

	@Column(name="acc_img")
	private String accImg; //상점에 들어갈 사진 

	@Column(name="acc_src")
	private String  accSrc; //obj의 경로
	
	
	@Builder
	public Accessory(Long accId, String accName, String accExplain, int accPrice, int accHp, String accImg,
			String accSrc) {
		super();
		this.accId = accId;
		this.accName = accName;
		this.accExplain = accExplain;
		this.accPrice = accPrice;
		this.accImg = accImg;
		this.accSrc = accSrc;
	}

}
