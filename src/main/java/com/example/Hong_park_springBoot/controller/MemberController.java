package com.example.Hong_park_springBoot.controller;

import com.example.Hong_park_springBoot.dto.MemberForm;
import com.example.Hong_park_springBoot.entity.Member;
import com.example.Hong_park_springBoot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("members/new")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMembers(MemberForm form){
        //1.dto to entity
        System.out.println(form.toString());
        Member member = form.toEntity();
        //2.save entity to DB with repository
        System.out.println(member.toString());
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        //3.show page
        return "";
    }
}
