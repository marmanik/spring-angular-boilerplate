package com.springapp.mvc.rest;

import org.apache.log4j.Logger;
import com.springapp.mvc.service.JdbcCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    private static final Logger log = Logger.getLogger(CustomerResource.class);

    @Autowired
    private JdbcCustomerService jdbcCustomerService;

    @RequestMapping(value = "/customer" , method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String insertCustomer(@RequestBody String name, HttpServletRequest request){
        log.info("Post to insert customer");
        List<String> msisdnList = jdbcCustomerService.findByName(name);
        if (msisdnList.isEmpty()){
            jdbcCustomerService.insert(name);
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "/customer/{name}" , method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String loadCustomer(@PathVariable(value = "name") String name, HttpServletRequest request){
        log.info("Get to find customer");
        jdbcCustomerService.findByName(name);
        return "SUCCESS";
    }
}