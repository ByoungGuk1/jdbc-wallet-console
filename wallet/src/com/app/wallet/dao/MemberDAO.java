package com.app.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.wallet.dto.MemberDTO;
import com.app.wallet.query.MemberQuery;
import com.app.wallet.util.DBUtil;

public class MemberDAO {

	public int insertMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(MemberQuery.INSERT_MEMBER);

			pst.setString(1, member.getMemberEmail());
			pst.setString(2, member.getMemberPassword());
			pst.setString(3, member.getMemberNickname());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("회원 등록 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	public MemberDTO selectMemberByEmail(String email) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_EMAIL);
			pst.setString(1, email);
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeMember(rs);
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException("회원 이메일 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public MemberDTO selectMemberById(int memberId) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_ID);
			pst.setInt(1, memberId);
			rs = pst.executeQuery();

			if (rs.next()) {
				return makeMember(rs);
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException("회원 ID 조회 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
	}

	public int updateMemberNickname(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			conn = DBUtil.dbConnect();
			pst = conn.prepareStatement(MemberQuery.UPDATE_MEMBER_NICKNAME);

			pst.setString(1, member.getMemberNickname());
			pst.setInt(2, member.getId());

			return pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("회원 닉네임 수정 중 오류가 발생했습니다.", e);
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
	}

	private MemberDTO makeMember(ResultSet rs) throws SQLException {
		return MemberDTO.builder().id(rs.getInt("ID")).memberEmail(rs.getString("MEMBER_EMAIL"))
				.memberPassword(rs.getString("MEMBER_PASSWORD")).memberNickname(rs.getString("MEMBER_NICKNAME"))
				.memberRole(rs.getString("MEMBER_ROLE")).createdAt(rs.getDate("CREATED_AT"))
				.updatedAt(rs.getDate("UPDATED_AT")).build();
	}
}
