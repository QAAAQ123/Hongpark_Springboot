package com.example.Hong_park_springBoot.dto;

import com.example.Hong_park_springBoot.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class CoffeeForm {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity(){
        return new Coffee(id,name,price);
    }
}
