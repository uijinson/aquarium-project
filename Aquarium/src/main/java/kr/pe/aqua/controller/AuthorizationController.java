package kr.pe.aqua.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.aqua.model.Member;
import kr.pe.aqua.model.MemberRepository;
import kr.pe.aqua.service.JwtService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthorizationController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MemberRepository repository;

	@PostMapping("/logincheck/signin")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody Member member, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Member loginMember = repository.findMemberByMemIdAndPw(member.getMemId(), member.getPw());
			Optional.of(loginMember);
			String token = jwtService.create(loginMember);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("auth_token", token);
			resultMap.put("status", true);
			resultMap.put("data", loginMember);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody Member member) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			log.error("정보 조회 실패 ", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
