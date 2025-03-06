package com.example.Hong_park_springBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/quote")
    public String RandomQuote(Model model){
        String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지니라. " + "-허버드-",
                "고개 숙이지 마십시오. 세상은 똑바로 정면으로" + "바라보십시오. -헬렌 켈러-",
                "고난의 시기에 동요하지 않는 것,이것은 진정" + "칭찬받을 만한 뛰어한 인물의 증거이다 -베토벤-"
        };
        int randInt = (int)(Math.random() * quotes.length);
        model.addAttribute("randomQute",quotes[randInt]);
        return "quote";
    }
}
