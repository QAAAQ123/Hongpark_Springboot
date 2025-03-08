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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/members/new")
    public String newMemberForm(){
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
        return "redirect:/members/" + saved.getId();
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
        ArrayList<Member> memberList = memberRepository.findAll();
        //모델에 등록
        model.addAttribute("memberList",memberList);
        //뷰페이지
        return "members/index";
    }

    @GetMapping("members/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 가져오기
        Member member = memberRepository.findById(id).orElse(null);
        //데이터 모델에 등록
        model.addAttribute("member",member);
        //뷰 페이지(edit)
        return "members/edit";
    }

    @PostMapping("members/update")
    public String update(MemberForm form){
        //dto를 엔티티로 변환
        Member member = form.toEntity();
        //엔티티를 DB에 저장
        if (member != null) {
            memberRepository.save(member);
        }
        //뷰 페이지 리다이렉트(show)
        return "redirect:/members/" + member.getId();
    }

    @GetMapping("members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //엔티티에 등록
        log.info("삭제 요청이 들어왔습니다.");
        Member target = memberRepository.findById(id).orElse(null);
        //삭제
        if(target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제 되었습니다");
        }
        //리다이렉트
        return "redirect:/members";
    }

}
