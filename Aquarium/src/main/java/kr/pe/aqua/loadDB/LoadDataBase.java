package kr.pe.aqua.loadDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDataBase {
	//private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	public CommandLineRunner initDatabase(FishRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(Fish.builder().fid("a").pw(1).fishName("shark").fishExpain("the sea of the king").build()));
			log.info("Preloading " + repository.save(Fish.builder().fid("b").pw(2).fishName("nimo").fishExpain("the cutiest of the sea").build()));
			log.info("Preloading " + repository.save(Fish.builder().fid("c").pw(3).fishName("whale").fishExpain("the biggest").build()));
			log.info("Preloading " + repository.save(Fish.builder().fid("d").pw(4).fishName("octo").fishExpain("wicked").build()));
			log.info("Preloading " + repository.save(Fish.builder().fid("e").pw(5).fishName("octo2").fishExpain("wicked2").build()));
		};
	}
}
