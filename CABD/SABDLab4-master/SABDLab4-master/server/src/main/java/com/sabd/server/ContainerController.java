package com.sabd.server;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DataObjectController {
    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DataObject findById(@PathVariable long id, HttpServletRequest req, HttpServletResponse res) {
        if (req.getHeader("Tst") != null) {
            res.addHeader("Tst", req.getHeader("Tst"));
        }
        return new DataObject(id, RandomStringUtils.randomAlphanumeric(10));
    }
}
