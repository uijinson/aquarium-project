package kr.pe.aqua.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	
	public Member findMoneyByMemId(String memId);

	public Member findMemberByMemIdAndPw(String memId, int pw);

	public void deleteById(String memId);


}
