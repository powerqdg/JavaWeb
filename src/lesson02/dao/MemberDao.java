package lesson02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import lesson02.vo.Member;

public class MemberDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public ArrayList<Member> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS ORDER BY MNO");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
					.setMno(rs.getInt("MNO"))
					.setEmail(rs.getString("EMAIL"))
					.setMname(rs.getString("MNAME"))
					.setCreDate(rs.getDate("CRE_DATE"))
					.setModDate(rs.getDate("MOD_DATE")));
			}
			return members;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	public Member selectOne(int mno) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, mno);
			rs = stmt.executeQuery();
			rs.next();
			
			return new Member()
					.setMno(rs.getInt("MNO"))
					.setMname(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreDate(rs.getDate(("CRE_DATE")))
					.setModDate(rs.getDate("MOD_DATE"));
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	public void insert(Member member) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("INSERT INTO MEMBERS VALUES(MNO_SEQ.nextVal, ?, ?, ?, SYSDATE, SYSDATE)");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getMname());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	public void update(Member member) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("UPDATE MEMBERS SET MNAME = ?, EMAIL = ?, MOD_DATE = SYSDATE WHERE MNO = ?");
			stmt.setString(1, member.getMname());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, member.getMno());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	public void delete(int mno) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("DELETE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, mno);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT EMAIL, MNAME FROM MEMBERS WHERE EMAIL = ? AND PWD = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member()
						.setEmail(rs.getString("EMAIL"))
						.setMname(rs.getString("MNAME"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
}
