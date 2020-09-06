package kr.pe.aqua.model;

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
@SequenceGenerator(name = "VIL_SEQ_GEN", sequenceName="VIL_SEQ", initialValue = 1, allocationSize = 1)
public class Villain {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIL_SEQ_GEN")
	private Long vilId; // pk
	private int vilHp;
	private String vilSrc; //obj의 경로
	private int vilReward;
	
	@Builder
	public Villain(Long vilId, int vilHp,  String vilSrc,int vilReward) {
		super();
		this.vilId = vilId;
		this.vilHp = vilHp;
		this.vilSrc = vilSrc;
		this.vilReward = vilReward;
	}
}
