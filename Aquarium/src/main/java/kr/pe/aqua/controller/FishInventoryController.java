package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishInventory;
import kr.pe.aqua.model.FishInventoryRepository;
import kr.pe.aqua.model.Member;

@RestController
@RequestMapping("/testpage")
public class FishInventoryController {
		
	private final FishInventoryRepository repository;
	
	public FishInventoryController(FishInventoryRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "selectFish() 메소드 사용", notes = "사용자의 물고기 리스트")
	@GetMapping("/fish/select")
	public List<FishInventory> selectFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
		System.out.println("???? "+memId);
		List<FishInventory> myFish = repository.findFishByMemId(memId);
		System.out.println(myFish);
		return myFish;
	}
	
//	@ApiOperation(value = "buyFish() 메소드 사용", notes = "물고기 구매")
//    @GetMapping("/newFishes/buyFish")
//    public FishInventory buyFish(@ApiParam(value = "물고기 Id를 입력하세요.", required = true) @RequestParam Fish fishId,
//                         @ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam Member memId) {
//       FishInventory buyFish = FishInventory.builder().fishId(fishId).memId(memId).build();
//       return repository.save(buyFish);
//    }
	
	@ApiOperation(value = "buyFish() 메소드 사용", notes = "물고기 구매")
	   @GetMapping("/fish/buyFish")
	   public FishInventory buyFish(@ApiParam(value = "물고기 Id를 입력하세요.", required = true) @RequestParam Long fishId,
	                        @ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memId) {
	      
	      Fish Fish = new Fish();
	      Fish fishId1 = Fish.builder().fishId(fishId).build();
	      
	      Member Member = new Member();
	      Member memId1 = Member.builder().memId(memId).build();
	      
	      FishInventory buyFish = FishInventory.builder().fishId(fishId1).memId(memId1).build();
	      return repository.save(buyFish);
	   }
}












