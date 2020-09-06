package kr.pe.aqua.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishInventory;
import kr.pe.aqua.model.FishInventoryRepository;

public class FishInventoryController {
		
	private final FishInventoryRepository repository;
	
	public FishInventoryController(FishInventoryRepository repository) {
		this.repository = repository;
	}
	
	//사용자 개인이 가지고 있는 물고기 리스트 정보 보여주기 
	//memid로 fish테이블을 조회한 결과 출력
	/*
	 * SQL> select fi.fish_no, fi.fish_id, fi.mem_id, f.FISH_ID, f.FISH_EXPLAIN, f.FISH_HP, f.FISH_NAME, f.FISH_PRICE
		  2  from fish_inventory fi, fish f
		  3  where fi.mem_id='a'
		  4  and fi.fish_id = f.fish_id;
		
		   FISH_NO    FISH_ID MEM_ID        FISH_ID FISH_EXPLA    FISH_HP FISH_NAME  FISH_PRICE
		---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
		         1          1 a                   1 nimo                1 nimo              100
		         2          2 a                   2 shark               1 shark             200
		         3          1 a                   1 nimo                1 nimo              100
		         4          1 a                   1 nimo                1 nimo              100
		         5          1 a                   1 nimo                1 nimo              100
		         6          1 a                   1 nimo                1 nimo              100
	 */
	//참고
	//https://www.it-swarm.dev/ko/java/spring-jpa-%EC%A0%80%EC%9E%A5%EC%86%8C%EC%97%90%EC%84%9C-%EC%97%AC%EB%9F%AC-%ED%85%8C%EC%9D%B4%EB%B8%94%EC%9D%98-%EA%B2%B0%EA%B3%BC%EB%A5%BC-%EA%B2%B0%ED%95%A9%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95/809200204/
	//https://victorydntmd.tistory.com/208
	@ApiOperation(value = "selectFish() 메소드 사용", notes = "사용자의 물고기 리스트")
	@GetMapping("/fish/select")
	public List<FishInventory> selectFish(@ApiParam(value = "이메일을 입력해주세요", required = true) @RequestParam String memberId) {
		
		return repository.findFishByMemId(memberId);
		
		/*
		List<FishInventory> perFishInventory = repository.findFishByMemId(memberId);  //1. 사용자의 물고기 리스트가 들어왔어
		List<Fish> perFishList = null; //2. 변수선언, 사용자의 물고기 정보 담고 출력위한 list 공간

		//3. for문 안에서는 2번에서 담겨진 수만큼 수행이 될거야..고로, 중복이 되도 출력되서 list담겨야해.
		//1번에 있던 것중에 첫번쨰 물고기의 fishid로 조회를 했어.
		for(int i =0; i < perFishInventory.size(); i++) { 
			
			perFishList.add(perFishInventory.get(i).getFishId());
		}
	      
		return perFishList;
		 */
	}
}












