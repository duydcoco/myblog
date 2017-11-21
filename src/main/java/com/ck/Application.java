package com.ck;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by dudycoco on 17-11-17.
 */
@SpringBootApplication
@Controller
@RequestMapping("/")
public class Application {

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        Gson gson = new Gson();
        Map map = Maps.newHashMap();
        map.put("name","helloworld");
        String json = gson.toJson(map);
        return json;
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
