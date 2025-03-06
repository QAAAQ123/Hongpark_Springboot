package com.example.Hong_park_springBoot.dto;

import com.example.Hong_park_springBoot.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@ToString
@Slf4j
public class MemberForm {
    private String email;
    private String password;


    public Member toEntity(){
        return new Member(null,email,password);
    }
}
