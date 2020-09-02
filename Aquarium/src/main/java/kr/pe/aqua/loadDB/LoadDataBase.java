package kr.pe.aqua.loadDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishRepository;
import kr.pe.aqua.model.Member;
import kr.pe.aqua.model.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDataBase {
	//private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	public CommandLineRunner initDatabase(FishRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(Fish.builder().fishName("octo2").fishExpain("wicked2").build()));
		};
	}
	
	@Bean
	public CommandLineRunner MemberInitDatabase(MemberRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(Member.builder().memId("aaa@naver.com").pw(11).bowlName("aaa").build()));
			log.info("Preloading " + repository.save(Member.builder().memId("bb").pw(11).bowlName("aaa").build()));
		};
	}
}
