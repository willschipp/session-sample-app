package com.example.telco.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/state")
public class StateEndpoint {

    private static final String KEY = "sessionWord";

    @PostMapping
    public void save(@RequestBody String body, HttpSession session, HttpServletResponse response) throws Exception {
        session.setAttribute(KEY,body);
        //response code
        response.setStatus(HttpStatus.CREATED.value());
    }

    @GetMapping
    public String get(HttpSession session) throws Exception {
        String value = null;
        try {
            value = session.getAttribute(KEY).toString();
        }
        catch (NullPointerException e) {
            value = "nothing has been set yet"; //TODO fix this
        }
        return value;
    }


    @GetMapping("/fancy")
    public String getFancy(HttpSession session) throws Exception {
        return "fancy " + get(session);
    }

}
