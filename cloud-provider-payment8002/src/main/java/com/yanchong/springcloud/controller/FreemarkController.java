package com.yanchong.springcloud.controller;

import com.yanchong.springcloud.entities.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FreemarkController {


    @RequestMapping("/freemarkHelloworld")
    public  String  freemarkHelloworld(Model model){
        model.addAttribute("name","yanchong");
        model.addAttribute("age","18");

        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbb");
        list.add("cccccc");
        list.add("dd");
        model.addAttribute("list",list);


        model.addAttribute("person",new Payment(66,"000000d0d00d0d0d0d0"));
        return "freemark_helloworld";


    }
}
