package com.sparta.basicspringsession.service;

import com.sparta.basicspringsession.dto.MemberSaveRequestDto;
import com.sparta.basicspringsession.dto.MemberSaveResponseDto;
import com.sparta.basicspringsession.dto.MemberSimpleResponseDto;
import com.sparta.basicspringsession.entity.Member;
import com.sparta.basicspringsession.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional  // Request DTO -> entity -> Repository -> Response DTO ?
    public MemberSaveResponseDto saveMember(MemberSaveRequestDto memberSaveRequestDto) {
        Member newMember = new Member(memberSaveRequestDto.getName()); // Request DTO -> entity
        Member saveMember = memberRepository.save(newMember); // entity -> Repository ?

        return new MemberSaveResponseDto(saveMember.getName()); // Repository -> Response DTO ?
//      MemberSaveResponseDto member = new MemberSaveResponseDto(saveMember.getId(), saveMember.getName());
//      return member;
    }

    // Repository -> List<Member> -> List<MemberSimpleResponseDto>
    // Repository -> List<entity> -> List<Response DTO> ?
    // R은 @Transactional 안붙임
    public List<MemberSimpleResponseDto> getMemberList() {
        List<Member> newMemberList = memberRepository.findAll(); // Repository -> List<Member> ?

        List<MemberSimpleResponseDto> memberSaveResponseDtos = new ArrayList<>(); // List<MemberSimpleResponseDto> 생성
        for (Member member : newMemberList) {
            memberSaveResponseDtos.add(new MemberSimpleResponseDto(member.getId(), member.getName()));
        } // newMemberList -> memberSaveResponseDtos
          // List<Member> -> List<MemberSimpleResponseDto>
        return memberSaveResponseDtos;
    }
}
