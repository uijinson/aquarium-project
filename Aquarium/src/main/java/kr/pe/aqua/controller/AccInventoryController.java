package kr.pe.aqua.controller;

import java.util.List;

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
	
	@ApiOperation(value = "selectAcc() 메소드 사용", notes = "사용자의 악세사리 리스트")
	@GetMapping("/accessory/select")
	public List<AccInventory> selectAcc(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		System.out.println("------AccInven-------"+memId);
		List<AccInventory> myAcc = repository.findAccByMemId(memId);
		System.out.println(myAcc);
		return myAcc;
	}
	
	@ApiOperation(value = "buyAcc() 메소드 사용", notes = "악세서리 구매")
	@GetMapping("/fish/buyAcc")
	public AccInventory buyAcc(@ApiParam(value = "악세러리 Id를 입력하세요.", required = true) @RequestParam Long accId,
                        @ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
      
      Accessory ACC = new Accessory();
      Accessory accId1 = Accessory.builder().accId(accId).build();
      
      Member Member = new Member();
      Member memId1 = Member.builder().memId(memId).build();
      
      AccInventory buyAcc = AccInventory.builder().accId(accId1).memId(memId1).build();
      return repository.save(buyAcc);
   }
}
