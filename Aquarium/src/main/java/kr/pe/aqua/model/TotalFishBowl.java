package kr.pe.aqua.model;

/* 어항물고기리스트id
 * 어항id - memberId(memId)
 * 물고기id - fish(fishNo)
 * 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class TotalFishBowl {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PER_FISH_SEQ_GEN")
	private Long perFishNo; // 번호

	// 전체어항물고기리스트에서 멤버(회원)를 참조
	@ManyToOne
	@JoinColumn(name = "fishBowlId")
	private Member member;

	@OneToOne
	@JoinColumn(name = "Fish_ID")
	private Fish fish;

	@Builder
	public TotalFishBowl(Long perFishNo, Member member, Fish fish) {
		super();
		this.perFishNo = perFishNo;
		this.member = member;
		this.fish = fish;
	}

}
