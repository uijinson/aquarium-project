package kr.pe.aqua.loadDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.pe.aqua.model.AccInventory;
import kr.pe.aqua.model.AccInventoryRepository;
import kr.pe.aqua.model.Accessory;
import kr.pe.aqua.model.AccessoryRepository;
import kr.pe.aqua.model.EquipInventory;
import kr.pe.aqua.model.EquipInventoryRepository;
import kr.pe.aqua.model.Equipment;
import kr.pe.aqua.model.EquipmentRepository;
import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishInventory;
import kr.pe.aqua.model.FishInventoryRepository;
import kr.pe.aqua.model.FishRepository;
import kr.pe.aqua.model.Member;
import kr.pe.aqua.model.MemberRepository;
import kr.pe.aqua.model.Villain;
import kr.pe.aqua.model.VillainRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDataBase {
	// private static final Logger log =
	// LoggerFactory.getLogger(LoadDataBase.class);

	// member
	@Bean
	public CommandLineRunner MemberInitDatabase(MemberRepository repository) {
		return args -> {
			log.info("Preloading "
					+ repository.save(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()));
			log.info("Preloading "
					+ repository.save(Member.builder().memId("bbb@naver.com").pw(22).nickName("b2").build()));
			log.info("Preloading "
					+ repository.save(Member.builder().memId("1").pw(1).nickName("1").build()));
		};
	}

	// fish
	@Bean
	public CommandLineRunner FishinItDatabase(FishRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(Fish.builder().fishName("shark").fishExplain("wicked2").build()));
			log.info("Preloading " + repository.save(Fish.builder().fishName("nimo").fishExplain("nimo").build()));
		};
	}
	
	//accessory
	@Bean
	public CommandLineRunner AccessoryInitDatabase(AccessoryRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Accessory.builder().accName("sea plant").accExplain("sea plant1").accPrice(100).build()));
			log.info("Preloading " + repository.save(Accessory.builder().accName("Mermaid").accExplain("Mermaid1").accPrice(200).build()));
			log.info("Preloading " + repository.save(Accessory.builder().accName("SpongeBob").accExplain("SpongeBob1").accPrice(300).build()));
		};
	}

	//equipment
	@Bean
	public CommandLineRunner EquipmentInitDatabase(EquipmentRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Equipment.builder().equipName("gun").equipExplain("gun1").equipPrice(100).build()));
			log.info("Preloading " + repository.save(Equipment.builder().equipName("gun2").equipExplain("gun2").equipPrice(100).build()));
		};
	}
	
	//fishInventory
	@Bean
	public CommandLineRunner FishInventoryInitDatabase(FishInventoryRepository repository) {
		return args -> {
		
			log.info("Preloading " + repository.save(FishInventory.builder().fishId(Fish.builder().fishId(1L).fishName("shark").fishExplain("wicked2").fishHp(1).build())
					.memberId(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()).build()));
			
			//log.info("Preloading " + repository.save(FishInventory.builder().test("a").build()));
		};
	}
	
	//accInventory
	@Bean
	public CommandLineRunner AccInventoryInitDatabase(AccInventoryRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(AccInventory.builder().accId(Accessory.builder().accId(1L).accName("sea plant").accExplain("sea plant1").accPrice(100).build())
					.memberId(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()).build()));
//			log.info("Preloading " + repository.save(AccInventory.builder().accId(2).memberId("aaa@naver.com").build()));
//			log.info("Preloading " + repository.save(AccInventory.builder().accId(1).memberId("aaa@naver.com").build()));
		};
	}
	
	//equipInventory
	@Bean
	public CommandLineRunner EquipInventoryInitDatabase(EquipInventoryRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(EquipInventory.builder().equipId(Equipment.builder().equipId(1L).equipName("gun").equipExplain("gun1").equipPrice(100).build()
					).memberId(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()).build()));
//			log.info("Preloading " + repository.save(EquipInventory.builder().equipId(2).memberId("aaa@naver.com").build()));
//			log.info("Preloading " + repository.save(EquipInventory.builder().equipId(1).memberId("aaa@naver.com").build()));
		};
	}
	
	//villain
	@Bean
	public CommandLineRunner VillainInitDatabase(VillainRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Villain.builder().vilHp(1).vilReward(500).build()));
			log.info("Preloading " + repository.save(Villain.builder().vilHp(2).vilReward(500).build()));
			log.info("Preloading " + repository.save(Villain.builder().vilHp(3).vilReward(500).build()));
		};
	}
}
