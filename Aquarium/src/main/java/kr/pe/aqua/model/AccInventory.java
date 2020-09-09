package kr.pe.aqua.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name = "ACC_NO_GEN", sequenceName="ACC_NO", initialValue = 1, allocationSize = 1)
public class AccInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_NO_GEN")
	private Long accNo; // pk
	
	//다대일
	@ManyToOne
	@JoinColumn(name="acc_id")
	private Accessory accId; // fk - acc table의 pk  //Accessory -> int
	
	//다대일
	@ManyToOne
	@JoinColumn(name="mem_id")
	private Member memId; // fk - member table의 pk //Member -> String

	@Builder
	public AccInventory(Long accNo, Accessory accId, Member memId) {
		super();
		this.accNo = accNo;
		this.accId = accId;
		this.memId = memId;
	}
}
