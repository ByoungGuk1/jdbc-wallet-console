package com.app.wallet.service;

import com.app.wallet.dao.MemberDAO;
import com.app.wallet.dto.MemberDTO;

public class MemberService {

	private final MemberDAO memberDAO = new MemberDAO();

	public boolean signup(MemberDTO member) {
		MemberDTO foundMember = memberDAO.selectMemberByEmail(member.getMemberEmail());

		if (foundMember != null) {
			return false;
		}

		return memberDAO.insertMember(member) == 1;
	}

	public MemberDTO login(MemberDTO loginInput) {
		MemberDTO foundMember = memberDAO.selectMemberByEmail(loginInput.getMemberEmail());

		if (foundMember == null) {
			return null;
		}

		if (!foundMember.getMemberPassword().equals(loginInput.getMemberPassword())) {
			return null;
		}

		return foundMember;
	}

	public MemberDTO getMember(int memberId) {
		return memberDAO.selectMemberById(memberId);
	}

	public boolean updateNickname(MemberDTO member) {
		return memberDAO.updateMemberNickname(member) == 1;
	}
}
