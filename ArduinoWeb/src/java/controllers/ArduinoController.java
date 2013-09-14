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
import org.springframework.web.bind.annotation.ResponseBody;
import service.ComController;
import service.DropLoop;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/arduino")
public class ArduinoController {

    private static ComController comController;
    private String initMsg = "";
    private static String userPort;
    private DropLoop singleLoop;
    public static String console = "";

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {

        updateConsole(model);
        return "arduino";

    }

    private void setUserPort(String port) {
        if (userPort == null || userPort.length() < 1) {
            userPort = port;
        }
    }

    private String getUserPort() {
        return userPort;
    }

    private ComController getComController(ModelMap model) {
        if (comController == null) {
            comController = new ComController();
            comController.addPort(userPort);
            if (comController.initSerial()) {
                initMsg = "Połączono z SyncBox";
                System.out.println("Arduino connectted");
            } else {
                initMsg = "Nie można połączyć z SyncBox";
                System.err.println("Error connecting to arduino");
            }
        }
        if (comController.getSerial().getArduinoController() != this) {
            comController.getSerial().setArduinoController(this);
        }
        return comController;
    }

    @RequestMapping(method = RequestMethod.GET, params = "port")
    public String loadChar(@RequestParam String port, ModelMap model) {

        console = "";
        setUserPort(port);
        System.out.println(userPort);
        model.put("port", userPort);
        getComController(model);
        updateConsole(model);
        return "arduino";
    }

    @RequestMapping(value = "/arduino/changeSize/{size}", params = "size")
    public @ResponseBody
    void changeDropSize(@RequestParam String size, ModelMap model) {
        //console="";
        System.out.println("Change size " + size);
        getComController(model).changeDropSize(size);
        updateConsole(model);
    }

    @RequestMapping(value = "/arduino/changeSize2/{size}", params = "size")
    public @ResponseBody
    void changeSecondDropSize(@RequestParam String size, ModelMap model) {
        //console="";
        System.out.println("Change size 2 " + size);
        getComController(model).changeSecondDropSize(size);
        updateConsole(model);
    }

    @RequestMapping(value = "/arduino/singleDrop")
    public @ResponseBody
    void singleDrop(ModelMap model) {
        System.out.println("Single drop");
        //console="";
        getComController(model).singleDrop();
        updateConsole(model);
    }

    @RequestMapping(value = "/arduino/loopSingleDrop")
    public @ResponseBody
    void loopSingleDrop(ModelMap model) {
        System.out.println("Loop Single drop");        
        singleLoop = new DropLoop(this.getComController(model));
        singleLoop.single = true;
        Thread loop = new Thread(singleLoop);
        loop.start();
        updateConsole(model);
    }
    
    @RequestMapping(value = "/arduino/loopTwoDrops")
    public @ResponseBody
    void loopTwoDrops(ModelMap model) {
        System.out.println("Loop two drops");
        singleLoop = new DropLoop(this.getComController(model));
        singleLoop.single = false;
        Thread loop = new Thread(singleLoop);
        loop.start();
        updateConsole(model);
    }

    @RequestMapping(value = "/arduino/stopLoop")
    public @ResponseBody
    void stopLoop(ModelMap model) {
        System.out.println("Stop loop");
        singleLoop.run = false;
        updateConsole(model);
    }

    @RequestMapping(value = "/arduino/changeDelay/{delay}", params = "delay")
    public @ResponseBody
    void changePhotoDelay(@RequestParam String delay, ModelMap model) {
        System.out.println("Change photo delay");
        //console="";
        getComController(model).changePhotoDelay(delay);
        updateConsole(model);
    }

    @RequestMapping(value = "/arduino/twoDrops")
    public @ResponseBody
    void twoDrops(ModelMap model) {
        System.out.println("Two drop");
        //console="";
        getComController(model).twoDrops();
        updateConsole(model);
    }

    private void updateConsole(ModelMap model) {
        console += "\n---------------\n";
        model.put("console", console);
        //System.out.println(console);
    }
}
