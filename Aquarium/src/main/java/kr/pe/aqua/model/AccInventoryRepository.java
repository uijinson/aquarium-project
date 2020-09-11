package kr.pe.aqua.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccInventoryRepository extends JpaRepository<AccInventory, Long> {
	
	@Query(value="select ai.acc_no, ai.acc_id, ai.mem_id, a.acc_ID, a.acc_EXPLAIN, a.acc_NAME, a.acc_PRICE from acc_inventory ai, Accessory a "+
			"where ai.mem_id = ?1 " + 
			"and ai.acc_id = a.acc_id",
			nativeQuery = true)
	List<AccInventory> findAccByMemId(@Param("memId") String memId);
}
