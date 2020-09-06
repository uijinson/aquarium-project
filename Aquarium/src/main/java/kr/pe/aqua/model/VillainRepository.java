package kr.pe.aqua.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VillainRepository extends JpaRepository<Villain, Long> {

	Villain findByVilId(Long vilId);


}
