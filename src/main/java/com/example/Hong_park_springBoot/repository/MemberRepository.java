package com.example.Hong_park_springBoot.repository;

import com.example.Hong_park_springBoot.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface MemberRepository extends CrudRepository<Member,Long> {
    @Override
    ArrayList<Member> findAll();
}
