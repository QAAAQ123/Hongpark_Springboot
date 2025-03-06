package com.example.Hong_park_springBoot.repository;

import com.example.Hong_park_springBoot.entity.Member;
import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member,Long> {

}
