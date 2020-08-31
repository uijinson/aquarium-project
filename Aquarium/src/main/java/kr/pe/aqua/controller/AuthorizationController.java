package kr.pe.aqua.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.aqua.model.Fish;
import kr.pe.aqua.model.FishRepository;
import kr.pe.aqua.service.JwtService;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api")
@Slf4j
public class AuthorizationController {

	@Autowired
	private JwtService jwtService;	
	
	@Autowired
	private FishRepository repository;
	
	@PostMapping("/logincheck/signin")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody Fish fish, HttpServletResponse res){
		log.info("--- 로그인 버튼 클릭시에 실행되는 메소드 ---");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Fish loginFish = repository.findFishByFidAndPw(fish.getFid(), fish.getPw());
			
			//로그인 성공시 토큰 생성
			String token = jwtService.create(loginFish);
			//System.out.println(token);
			//토큰 정보는 request의 헤더로 보내고 나머지는 map에 담아두기
			res.setHeader("jwt-auth-token", token);
			
			System.out.println("생성된 token -- " + token);
			
			resultMap.put("auth_token", token);
			resultMap.put("status", true);
			resultMap.put("data", loginFish);
			
			status = HttpStatus.ACCEPTED;
		
		}catch(RuntimeException e) {
			log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		log.info("**************************" + resultMap);
		
		//리턴시 map과 상태 메세지를 함께 전송
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	//http://ip/api/info
	//JwtInterceptor의 메소드 실행 : 토큰이 있다면 검증 후 해당 메소드 실행/ 없다면 예외 발생해서 이 메소드 실행 안 함
	/*request로부터 JWT정보 획득해서 검수 후 서비스 진행
	 * 
	 */
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody Fish user){
		log.info("---- getInfo () -----------------");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			status = HttpStatus.ACCEPTED;
			
		}catch(RuntimeException e) {
			log.error("정보 조회 실패 ", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}






















