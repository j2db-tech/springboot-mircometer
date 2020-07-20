package com.j2db.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final BusinessLogic logic;

    @Autowired
    public AppController(BusinessLogic logic)
    {
      this.logic=logic;
    }

    @GetMapping("/invoke")
    public String invokeLogic()
    {
    logic.invoke();
    return "This is Home page";
    }


}
