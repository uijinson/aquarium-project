package kr.pe.aqua.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipInventoryRepository extends JpaRepository<EquipInventory, Long> {

	@Query(value = "select ei.equip_no, ei.equip_id, ei.mem_id, e.equip_id, e.equip_explain, e.equip_name, e.equip_price from equip_inventory ei, equipment e "
			+ "where ei.mem_id = ?1 " + "and ei.equip_id = e.equip_id ",
			nativeQuery = true)
	List<EquipInventory> findEquipByMemId(@Param("memId") String memId);
}
