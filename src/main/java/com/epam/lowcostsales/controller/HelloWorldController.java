package com.epam.lowcostsales.controller;

import com.epam.lowcostsales.service.SimpleService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldController extends MultiActionController {

    private SimpleService simpleService;

    public HelloWorldController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    public ModelAndView about(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        return new ModelAndView("about", "message", simpleService.getMessage());
    }
}
