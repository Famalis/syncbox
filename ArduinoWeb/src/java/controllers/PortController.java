/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ComController;

/**
 *
 * @author sergi_000
 */
@Controller
@RequestMapping("/port")
public class PortController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        
        return "port";

    }
}
