package com.sparta.basicspringsession.controller;

import com.sparta.basicspringsession.dto.*;
import com.sparta.basicspringsession.entity.Member;
import com.sparta.basicspringsession.repository.MemberRepository;
import com.sparta.basicspringsession.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/members/update/{id}")
    public ResponseEntity<MemberUpdateResponseDto> updateMember(@RequestBody MemberUpdateRequestDto memberUpdateRequestDto, @PathVariable Long id) {
        return ResponseEntity.ok(memberService.updateMember(memberUpdateRequestDto, id));
    }

    @DeleteMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "삭제 완료";
    }
}
