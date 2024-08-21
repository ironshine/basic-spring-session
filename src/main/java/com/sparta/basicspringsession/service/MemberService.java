package com.sparta.basicspringsession.service;

import com.sparta.basicspringsession.dto.MemberSaveRequestDto;
import com.sparta.basicspringsession.dto.MemberSaveResponseDto;
import com.sparta.basicspringsession.entity.Member;
import com.sparta.basicspringsession.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponseDto saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        Member newMember = new Member(memberSaveRequestDto.getName()); // Request DTO -> entity
        Member saveMember = memberRepository.save(newMember); // entity -> Repository ?

        return new MemberSaveResponseDto(saveMember.getName()); // Repository -> Response DTO ?
//      MemberSaveResponseDto member = new MemberSaveResponseDto(saveMember.getId(), saveMember.getName());
//      return member;
    }
}
