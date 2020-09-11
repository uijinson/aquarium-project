package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.AccInventory;
import kr.pe.aqua.model.AccInventoryRepository;
import kr.pe.aqua.model.Accessory;
import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishInventory;
import kr.pe.aqua.model.Member;

@RestController
@RequestMapping("/testpage")
public class AccInventoryController {
	
	private final AccInventoryRepository repository;
	
	public AccInventoryController(AccInventoryRepository repository) {
		this.repository = repository;
	}
	
	//acc inventory 리스트
	@ApiOperation(value = "selectAcc() 메소드 사용", notes = "사용자의 악세사리 리스트")
	@GetMapping("/accessory/select")
	public List<AccInventory> selectAcc(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		List<AccInventory> myAcc = repository.findAccByMemId(memId);
		
		return myAcc;
	}
	
	//악세서리 구매 -> acc inventory 담기
	@ApiOperation(value = "buyAcc() 메소드 사용", notes = "악세서리 구매")
	@GetMapping("/accessory/buyAcc")
	public AccInventory buyAcc(@ApiParam(value = "악세러리 Id를 입력하세요.", required = true) @RequestParam Long accId,
                        		@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
	  Accessory accId1 = Accessory. builder().accId(accId).build();
      Member memId1 = Member.builder().memId(memId).build();																		   
      AccInventory buyAcc = AccInventory.builder().accId(accId1).memId(memId1).build();
      
      return repository.save(buyAcc);
   }
	
   //accInventory에 담겨있는 것 중에 삭제
   @ApiOperation(value = "deleteAcc() 메소드 사용", notes = "accInventory에 담겨있는 악세서리 삭제")
   @DeleteMapping("/deleteAcc")
   public void deleteAcc(@ApiParam(value = "악세서리 No를 입력하세요.", required = true) @RequestParam Long accNo) {
      repository.deleteById(accNo);
   }
}