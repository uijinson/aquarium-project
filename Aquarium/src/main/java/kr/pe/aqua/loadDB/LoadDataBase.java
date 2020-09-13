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
			log.info("Preloading " + repository.save(Member.builder().memId("aaa@naver.com").pw(11).money(5000).nickName("b1").build()));
			log.info("Preloading " + repository.save(Member.builder().memId("bbb@naver.com").pw(22).nickName("b2").build()));
			log.info("Preloading " + repository.save(Member.builder().memId("aa").pw(1).nickName("a").build()));
			log.info("Preloading " + repository.save(Member.builder().memId("1").pw(1).money(5000).nickName("1").build()));
		};
	}

	// fish
	@Bean
	public CommandLineRunner FishinItDatabase(FishRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Fish.builder().fishName("turtle").fishExplain("꽃을 품은 거북이").fishImg("/img/fish/turtle.jpg").fishPrice(1000).build()));
			log.info("Preloading " + repository.save(Fish.builder().fishName("crab").fishExplain("생각보다 엄청 빠른 꽃게").fishImg("/img/fish/crab.jpg").fishPrice(2000).build()));
			log.info("Preloading " + repository.save(Fish.builder().fishName("shark").fishExplain("제일 사나운 상어").fishImg("/img/fish/shark.jpg").fishPrice(3000).build()));
			log.info("Preloading " + repository.save(Fish.builder().fishName("alligator").fishExplain("다리 짧은 악어").fishImg("/img/fish/alligator.jpg").fishPrice(4000).build()));
		};
	}

	// accessory
	@Bean
	public CommandLineRunner AccessoryInitDatabase(AccessoryRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Accessory.builder().accName("castle").accExplain("공주가 살 것 같은 성").accImg("/img/acc/castle.jpg").accPrice(7000).build()));
			log.info("Preloading " + repository.save(Accessory.builder().accName("fence").accExplain("적 침입 방지 울타리").accImg("/img/acc/fence.jpg").accPrice(500).build()));
			log.info("Preloading " + repository.save(Accessory.builder().accName("flower").accExplain("바다에서 피는 꽃").accImg("/img/acc/flower.jpg").accPrice(3000).build()));
			log.info("Preloading " + repository.save(Accessory.builder().accName("seatree").accExplain("바다에서 자라는 나무").accImg("/img/acc/tree.jpg").accPrice(5000).build()));
			log.info("Preloading " + repository.save(Accessory.builder().accName("seagrass").accExplain("의식주 공급하는 해초").accImg("/img/acc/grass.jpg").accPrice(4000).build()));
		};
	}

	// equipment
	@Bean
	public CommandLineRunner EquipmentInitDatabase(EquipmentRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Equipment.builder().equipName("gun").equipExplain("강력한 총").equipImg("/img/equip/gun.jpg").equipPrice(10000).build()));
			log.info("Preloading " + repository.save(Equipment.builder().equipName("arrow").equipExplain("덜 강력한 화살").equipImg("/img/equip/arrow.jpg").equipPrice(8000).build()));
		};
	}

	// fishInventory
	@Bean
	public CommandLineRunner FishInventoryInitDatabase(FishInventoryRepository repository) {
		return args -> {

			log.info("Preloading " + repository.save(FishInventory.builder()
					.fishId(Fish.builder().fishId(1L).fishName("turtle").fishExplain("귀여운 거북이").fishHp(1).build())
					.memId(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()).build()));
 
			log.info("Preloading " + repository.save(FishInventory.builder()
					.fishId(Fish.builder().fishId(1L).fishName("alligator").fishExplain("무서운 악어").fishHp(1).build())
					.memId(Member.builder().memId("1").pw(1).nickName("1").build()).build()));
		};
	}

	// accInventory
	@Bean
	public CommandLineRunner AccInventoryInitDatabase(AccInventoryRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(AccInventory.builder()
					.accId(Accessory.builder().accId(1L).accName("sea plant").accExplain("sea plant1").accPrice(100)
							.build())
					.memId(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()).build()));
			log.info("Preloading " + repository.save(AccInventory.builder()
					.accId(Accessory.builder().accId(1L).accName("sea plant").accExplain("sea plant1").accPrice(100)
							.build())
					.memId(Member.builder().memId("1").pw(1).nickName("b1").build()).build()));

		};
	}

	// equipInventory
	@Bean
	public CommandLineRunner EquipInventoryInitDatabase(EquipInventoryRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(EquipInventory.builder()
					.equipId(Equipment.builder().equipId(1L).equipName("gun").equipExplain("gun1").equipPrice(100)
							.build())
					.memId(Member.builder().memId("aaa@naver.com").pw(11).nickName("b1").build()).build()));
	
		log.info("Preloading " + repository.save(EquipInventory.builder()
				.equipId(Equipment.builder().equipId(1L).equipName("gun").equipExplain("gun1").equipPrice(100)
						.build())
				.memId(Member.builder().memId("1").pw(1).nickName("b1").build()).build()));
	};
	}

	// villain
	@Bean
	public CommandLineRunner VillainInitDatabase(VillainRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Villain.builder().vilHp(1).vilReward(500).build()));
			log.info("Preloading " + repository.save(Villain.builder().vilHp(2).vilReward(500).build()));
			log.info("Preloading " + repository.save(Villain.builder().vilHp(3).vilReward(500).build()));
		};
	}
}
