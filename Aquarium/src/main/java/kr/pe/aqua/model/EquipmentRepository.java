package kr.pe.aqua.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}