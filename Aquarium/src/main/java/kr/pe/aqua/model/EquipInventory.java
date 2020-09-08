package kr.pe.aqua.model;

import javax.persistence.Column;
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
@SequenceGenerator(name = "EQUIP_NO_GEN", sequenceName="EQUIP_NO", initialValue = 1, allocationSize = 1)
public class EquipInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EQUIP_NO_GEN")
	@Column(name="equip_no")
	private Long equipNo; // pk
	
	//다대일
	@ManyToOne
	@JoinColumn(name="equip_id")
	private Equipment equipId; // fk - equip table의 pk  //Equipment -> int
	
	//다대일
	@ManyToOne
	@JoinColumn(name="mem_id")
	private Member memId; // fk - member table의 pk //Member -> String

	@Builder
	public EquipInventory(Long equipNo, Equipment equipId, Member memId) {
		super();
		this.equipNo = equipNo;
		this.equipId = equipId;
		this.memId = memId;
	}
}
