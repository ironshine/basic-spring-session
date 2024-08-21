package com.sparta.basicspringsession.controller;

import com.sparta.basicspringsession.dto.MemberSaveRequestDto;
import com.sparta.basicspringsession.dto.MemberSaveResponseDto;
import com.sparta.basicspringsession.entity.Member;
import com.sparta.basicspringsession.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public MemberSaveResponseDto saveMember(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        Member newMember = new Member(memberSaveRequestDto.getName()); // Request DTO -> entity
        Member saveMember = memberRepository.save(newMember); // entity -> Repository ?

        return new MemberSaveResponseDto(saveMember.getId(), saveMember.getName()); // Repository -> Response DTO
//      MemberSaveResponseDto member = new MemberSaveResponseDto(saveMember.getId(), saveMember.getName());
//      return member;
    }
}
