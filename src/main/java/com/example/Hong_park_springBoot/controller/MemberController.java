package com.example.Hong_park_springBoot.controller;

import com.example.Hong_park_springBoot.dto.MemberForm;
import com.example.Hong_park_springBoot.entity.Member;
import com.example.Hong_park_springBoot.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUpPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMembers(MemberForm form){
        //1.dto to entity
        log.info(form.toString());
        Member member = form.toEntity();
        //2.save entity to DB with repository
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        //3.show page
        return "";
    }

    @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model){
        //리퍼지터리 명령으로 엔티티 꺼냄
        Member member = memberRepository.findById(id).orElse(null);
        //모델에 데이터 등록
        model.addAttribute("member",member);
        //뷰페이지
        return "/members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        //엔티티
        ArrayList<Member> members = memberRepository.findAll();
        //모델에 등록
        model.addAttribute("members",members);
        //뷰페이지
        return "members/index";
    }
}
