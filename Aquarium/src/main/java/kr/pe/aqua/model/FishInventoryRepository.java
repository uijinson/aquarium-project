package kr.pe.aqua.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FishInventoryRepository extends JpaRepository<FishInventory, Long> {

	@Query(value="SELECT fi.fish_no, fi.fish_id, fi.mem_id, f.FISH_ID, f.FISH_EXPLAIN, f.FISH_HP, f.FISH_NAME, f.FISH_PRICE FROM fish_inventory fi, fish f"+
				"where fi.mem_id = :memId" + 
				"and fi.fish_id = f.fish_id",
				nativeQuery = true)
	List<FishInventory> findFishByMemId(@Param("memId") String memId);

	
}
