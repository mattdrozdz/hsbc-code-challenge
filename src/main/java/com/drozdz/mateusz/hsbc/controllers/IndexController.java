package com.drozdz.mateusz.hsbc.controllers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
@Api(
        description = "Redirection to Api Documentation"
)
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String getDocs() {
        return "redirect:swagger-ui.html";
    }

}
