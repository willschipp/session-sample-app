package com.example.telco.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
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
        String value = null;
        try {
            value = session.getAttribute(KEY).toString();
        }
        catch (NullPointerException e) {
            value = "nothing has been set yet"; //TODO fix this
        }
        return value;
    }


    @RequestMapping( value="/fancy", method= RequestMethod.GET)
    public String getFancy(HttpSession session) throws Exception {
        return "fancy " + get(session);
    }

}
