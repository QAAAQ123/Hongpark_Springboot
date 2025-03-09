package com.example.Hong_park_springBoot.service;

import com.example.Hong_park_springBoot.dto.CoffeeForm;
import com.example.Hong_park_springBoot.entity.Coffee;
import com.example.Hong_park_springBoot.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm form){
        Coffee saveEntity = form.toEntity();
        if(saveEntity.getId() != null)
            return null;
        return coffeeRepository.save(saveEntity);
    }

    public Coffee update(CoffeeForm dto,Long id){
        //id로 데이터 꺼내오기
        Coffee coffee = dto.toEntity();

        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != coffee.getId())
            return null;

        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        //타겟이 null이면 null리턴
        if(target == null)
            return null;
        //타겟이 존재하면 삭제후 리턴
        coffeeRepository.delete(target);
        return target;
    }
}
