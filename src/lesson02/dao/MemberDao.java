package lesson02.dao;

import java.util.ArrayList;

import lesson02.vo.Member;

public interface MemberDao {
	ArrayList<Member> selectList() throws Exception;
	Member selectOne(int mno) throws Exception;
	void insert(Member member) throws Exception;
	void update(Member member) throws Exception;
	void delete(int mno) throws Exception;
	Member exist(String email, String password) throws Exception;
}
