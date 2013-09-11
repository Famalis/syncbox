/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/arduino")
public class ArduinoController {
    
    private int sum = 0;
    
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "arduino";

    }
    
    @RequestMapping(method = RequestMethod.POST, params = "input")
    public String loadChar(@RequestParam String input, ModelMap model) {
        
        System.out.println(input);
        sum+=Integer.parseInt(input);
        model.put("msg", sum);
        return "arduino";
    }
}
