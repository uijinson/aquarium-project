package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.AccInventory;
import kr.pe.aqua.model.AccInventoryRepository;
import kr.pe.aqua.model.Fish;

public class AccInventoryController {
	
	private final AccInventoryRepository repository;
	
	public AccInventoryController(AccInventoryRepository repository) {
		this.repository = repository;
	}
	
	/*
	 * select ai.acc_no, ai.acc_id, ai.mem_id, a.acc_ID, a.acc_EXPLAIN, a.acc_NAME, a.acc_PRICE
		 from acc_inventory ai, Accessory a
		 where ai.mem_id='a'
		 and ai.acc_id = a.acc_id;
	 */
	public List<AccInventory> selectAcc(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		
		return repository.findAccByMemId(memId);
	}
}
