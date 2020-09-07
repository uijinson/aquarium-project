package kr.pe.aqua.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.aqua.exception.NotFoundException;
import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishRepository;

@RestController
@Api(value = "FishController specification v1")
@RequestMapping("/api")
public class FishController {

   private final FishRepository repository;

   public FishController(FishRepository repository) {
      this.repository = repository;
   }

   @ApiResponses({ @ApiResponse(code = 200, message = "맞아요!"), @ApiResponse(code = 500, message = "서버 에러"),
         @ApiResponse(code = 403, message = "금지"), @ApiResponse(code = 404, message = "어디있지?"),
         @ApiResponse(code = 401, message = "허가노노") })

   // 전체 물고기 리스트
   @ApiOperation(value = "allFish() 메소드 사용", notes = "전체 물고기 리스트")
   @GetMapping("/allfish")
   List<Fish> allFish() {
      return repository.findAll();
   }

   // 물고기 추가
   @ApiOperation(value = "newFish() 메소드 사용", notes = "새로운 물고기 추가")
   @GetMapping("/newFishes")
   public Fish newFish(@ApiParam(value = "물고기 이름을 입력하세요.", required = true) @RequestParam String fishName,
                  @ApiParam(value = "물고기의 설명을 입력하세요.", required = true) @RequestParam String fishExplain,
                  @ApiParam(value = "물고기의 가격을 입력하세요.", required = true) @RequestParam int fishPrice,
                  @ApiParam(value = "물고기의 헬스포인트를 입력하세요.", required = true) @RequestParam int fishHp,
                  @ApiParam(value = "물고기의 fishImg를 입력하세요.", required = false) @RequestParam String fishImg,
                  @ApiParam(value = "물고기의 fishSrc를 입력하세요.", required = false) @RequestParam String fishSrc) {
      Fish newFish = Fish.builder().fishName(fishName).fishExplain(fishExplain).fishPrice(fishPrice).fishHp(fishHp)
            .fishImg(fishImg).fishSrc(fishSrc).build();
      return repository.save(newFish);
   }

   // 물고기 한 마리 검색
   @ApiOperation(value = "Fish one() 메소드 사용", notes = "물고기 한 마리 검색")
   @GetMapping("/allfishes/{fishId}")
   public Fish oneFish(@ApiParam(value = "fish번호", required = true) @RequestParam Long fishId) {

      return repository.findById(fishId).orElseThrow(() -> new NotFoundException(fishId));
   }

   // 물고기 정보 수정
   @ApiOperation(value = "updateFish() 메소드 사용", notes = "기존 물고기 수정")
   @PutMapping("/updateFishes/{fishId}")
   public Optional<Object> updateFish(   @ApiParam(value = "물고기의 번호를 입력하세요.", required = false) @RequestParam Long fishId,
                              @ApiParam(value = "물고기의 이름을 입력하세요.", required = true) @RequestParam String fishName,
                              @ApiParam(value = "물고기의 설명을 입력하세요.", required = true) @RequestParam String fishExplain,
                              @ApiParam(value = "물고기의 가격을 입력하세요.", required = true) @RequestParam int fishPrice,
                              @ApiParam(value = "물고기의 헬스포인트를 입력하세요.", required = true) @RequestParam int fishHp,
                              @ApiParam(value = "물고기의 fishImg를 입력하세요.", required = false) @RequestParam String fishImg,
                              @ApiParam(value = "물고기의 fishSrc를 입력하세요.", required = false) @RequestParam String fishSrc) {

      Fish newFish = Fish.builder().fishName(fishName).fishExplain(fishExplain).fishPrice(fishPrice).fishHp(fishHp).fishImg(fishImg).fishSrc(fishSrc).build();
      return repository.findById(fishId).map(fish -> {
         fish.setFishName(newFish.getFishName());
         fish.setFishExplain(newFish.getFishExplain());
         fish.setFishPrice(newFish.getFishPrice());
         fish.setFishHp(newFish.getFishHp());
         fish.setFishImg(newFish.getFishImg());
         fish.setFishSrc(newFish.getFishSrc());
         return repository.save(fish);
      });
   }

   //물고기 삭제
   @ApiOperation(value = "deleteFish() 메소드 사용", notes = "물고기 삭제")
   @DeleteMapping("/deleteFish/{fishId}")
   public void deleteFish(@ApiParam(value = "fish번호", required = true) @RequestParam Long fishId) {
      repository.deleteById(fishId);
   }

}