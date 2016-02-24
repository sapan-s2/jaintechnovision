package com.jaintechnovision.endpoint;

import com.jaintechnovision.service.EmailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sjain on 2/24/2016.
 */

@RestController
public class EmailEndPoint {

    @Autowired
    EmailClient emailEndPoint;


    @RequestMapping(method = RequestMethod.GET , value = "/health", produces = "application/json")
    @ResponseBody
    public String healthCheck() {
        return "SpringBoot Health";
    }


    @RequestMapping(method = RequestMethod.GET , value = "/ContactUs", produces = "application/json")
    @ResponseBody
    public void contactUs() {
         emailEndPoint.doSend();
    }

}
