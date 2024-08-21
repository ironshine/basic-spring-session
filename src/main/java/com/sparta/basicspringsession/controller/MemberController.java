package com.sparta.basicspringsession.controller;

import com.sparta.basicspringsession.dto.MemberSaveRequestDto;
import com.sparta.basicspringsession.dto.MemberSaveResponseDto;
import com.sparta.basicspringsession.dto.MemberSimpleResponseDto;
import com.sparta.basicspringsession.entity.Member;
import com.sparta.basicspringsession.repository.MemberRepository;
import com.sparta.basicspringsession.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> saveMember(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return ResponseEntity.ok(memberService.saveMember(memberSaveRequestDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberSimpleResponseDto>> getMemberList() {
        return ResponseEntity.ok(memberService.getMemberList());
    }
}
