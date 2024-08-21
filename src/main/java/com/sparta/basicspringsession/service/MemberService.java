package com.sparta.basicspringsession.service;

import com.sparta.basicspringsession.dto.*;
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
    // CRUD중 R은 @Transactional 안붙임
    public List<MemberSimpleResponseDto> getMemberList() {
        List<Member> newMemberList = memberRepository.findAll(); // Repository -> List<Member> ?

        List<MemberSimpleResponseDto> memberSaveResponseDtos = new ArrayList<>(); // List<MemberSimpleResponseDto> 생성
        for (Member member : newMemberList) {
            memberSaveResponseDtos.add(new MemberSimpleResponseDto(member.getId(), member.getName()));
        } // newMemberList -> memberSaveResponseDtos
          // List<Member> -> List<MemberSimpleResponseDto>
        return memberSaveResponseDtos;
    }

    @Transactional // Repository -> entity -> entity RequestDTO 값받아서 수정 -> ResponseDTO
    public MemberUpdateResponseDto updateMember(MemberUpdateRequestDto memberUpdateRequestDto, Long id) {
        Member newMember = memberRepository.findById(id).orElseThrow(() -> new NullPointerException("id없다")); // Repository -> entity ?

        newMember.update(memberUpdateRequestDto.getName()); // entity RequestDTO 값받아서 수정

        return new MemberUpdateResponseDto(newMember.getId(), newMember.getName()); // entity -> ResponseDTO

    }

    @Transactional // Repository 에서 entity 찾고 Repository 에서 찾은 entity 삭제
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id).orElseThrow(() -> new NullPointerException("id없다")));
    }
}
