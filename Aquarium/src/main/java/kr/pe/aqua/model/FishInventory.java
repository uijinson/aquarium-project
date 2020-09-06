package kr.pe.aqua.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name = "FISH_NO_GEN", sequenceName="FISH_NO", initialValue = 1, allocationSize = 1)
public class FishInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FISH_NO_GEN")
	private Long fishNo; // pk
	
	private String test;
	//다대일
	@ManyToOne
	@JoinColumn(name="fish_id")
	private Fish fishId; // fk - fish table의 pk 
	
	//다대일
	@ManyToOne
	@JoinColumn(name="mem_id")
	private Member memberId; // fk - member table의 pk  

	@Builder
	public FishInventory(Long fishNo, String test, Fish fishId, Member memberId) {
		super();
		this.fishNo = fishNo;
		this.test = test;
		this.fishId = fishId;
		this.memberId = memberId;
	}

}
