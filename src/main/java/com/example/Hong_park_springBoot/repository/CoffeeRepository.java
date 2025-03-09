package com.example.Hong_park_springBoot.repository;

import com.example.Hong_park_springBoot.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CoffeeRepository extends CrudRepository<Coffee,Long> {
    @Override
    ArrayList<Coffee> findAll();
}
