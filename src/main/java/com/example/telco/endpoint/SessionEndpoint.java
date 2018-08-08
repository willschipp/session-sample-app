package com.example.telco.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionEndpoint {

    private static final String KEY = "sessionWord";

    @RequestMapping(method= RequestMethod.POST)
    public void save(@RequestBody String body, HttpSession session, HttpServletResponse response) throws Exception {
        session.setAttribute(KEY,body);
        //response code
        response.setStatus(HttpStatus.CREATED.value());
    }

    @RequestMapping(method= RequestMethod.GET)
    public String get(HttpSession session) throws Exception {
        return session.getAttribute(KEY).toString();
    }
}
