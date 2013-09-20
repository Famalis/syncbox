/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Preset;
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
    private static String userPort;
    private DropLoop singleLoop, doubleLoop;
    public static String console = "";
    public int dropSize1, dropSize2, photoDelay;
    public String name = "Nazwa";

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {

        constatData(model);
        return "arduino";

    }

    private void constatData(ModelMap model) {
        getComController(model).loadPresets();
        String[] names = new String[ComController.presets.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = ComController.presets.get(i).name;
        }
        model.put("presets", names);
        if (getComController(model).connected) {
            model.put("connectionMsg", "SyncBox podłączony na ");
            model.put("port", getComController(model).getSerial().port);
        } else {
            model.put("connectionMsg", "Brak połączenia z SyncBox ");
        }
        model.put("size1", dropSize1);
        model.put("size2", dropSize2);
        model.put("delay", photoDelay);
        model.put("name", name);
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
                System.out.println("Arduino connectted");
            } else {
                System.err.println("Error connecting to arduino");
            }
        }
        if (comController.getSerial().getArduinoController() != this) {
            comController.getSerial().setArduinoController(this);
        }
        return comController;
    }

    @RequestMapping(method = RequestMethod.GET, params = "port")
    public String setPort(@RequestParam String port, ModelMap model) {

        console = "";
        setUserPort(port);
        System.out.println(userPort);
        getComController(model);
        constatData(model);
        model.put("size1", "5");
        model.put("size2", "5");
        model.put("delay", "5");
        return "arduino";
    }

    @RequestMapping(value = "/arduino/changeSize/{size}", params = "size")
    public @ResponseBody
    String changeDropSize(@RequestParam String size, ModelMap model) {
        console = "";
        System.out.println("Change size " + size);
        dropSize1 = Integer.parseInt(size);
        getComController(model).changeDropSize(size);
        updateConsole(model);
        return console;
    }

    @RequestMapping(value = "/arduino/changeSize2/{size}", params = "size")
    public @ResponseBody
    String changeSecondDropSize(@RequestParam String size, ModelMap model) {
        console = "";
        System.out.println("Change size 2 " + size);
        dropSize2 = Integer.parseInt(size);
        getComController(model).changeSecondDropSize(size);
        updateConsole(model);
        return console;
    }

    @RequestMapping(value = "/arduino/changeDelay/{delay}", params = "delay")
    public @ResponseBody
    String changePhotoDelay(@RequestParam String delay, ModelMap model) {
        System.out.println("Change photo delay");
        console = "";
        photoDelay = Integer.parseInt(userPort);
        getComController(model).changePhotoDelay(delay);
        updateConsole(model);
        return console;
    }

    @RequestMapping(value = "/arduino/singleDrop")
    public @ResponseBody
    String singleDrop(ModelMap model) {
        System.out.println("Single drop");
        console = "";
        getComController(model).singleDrop();
        updateConsole(model);
        return console;
    }

    @RequestMapping(value = "/arduino/twoDrops")
    public @ResponseBody
    String twoDrops(ModelMap model) {
        System.out.println("Two drop");
        console = "";
        getComController(model).twoDrops();
        updateConsole(model);
        return console;
    }

    @RequestMapping(value = "/arduino/loopSingleDrop/{check}")
    public @ResponseBody
    void loopSingleDrop(@RequestParam String check, ModelMap model) {
        System.out.println("Loop Single drop: " + check);
        if (!check.equals("none")) {
            try {
                if (singleLoop.run) {
                    singleLoop.run = false;
                }
            } catch (NullPointerException nullEx) {
            }
            singleLoop = new DropLoop(this.getComController(model));
            if (check.equals("single")) {
                singleLoop.single = true;
            } else {
                singleLoop.single = false;
            }
            Thread loop = new Thread(singleLoop);
            loop.start();
        } else {
            try {
            singleLoop.run = false;
            } catch (NullPointerException nullEx) {
            }
        }
    }

    @RequestMapping(value = "/arduino/loopTwoDrops/{check}")
    public @ResponseBody
    void loopTwoDrops(@RequestParam String check, ModelMap model) {
        System.out.println("Loop two drops: " + check);
        if (check.equals("true")) {
            try {
                if (singleLoop.run) {
                    singleLoop.run = false;
                }
            } catch (NullPointerException nullEx) {
            }
            singleLoop = new DropLoop(this.getComController(model));
            singleLoop.single = false;
            Thread loop = new Thread(singleLoop);
            loop.start();
        } else {
            singleLoop.run = false;
        }
    }

    @RequestMapping(value = "/arduino/stopLoop")
    public @ResponseBody
    void stopLoop(ModelMap model) {
        System.out.println("Stop loop");
        try {
        singleLoop.run = false;
        } catch (NullPointerException nullEx) {
            
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "preset")
    public String loadPreset(@RequestParam String preset, ModelMap model) {
        System.out.println("Load preset");
        Integer index = Integer.parseInt(preset);
        Preset p = ComController.presets.get(index - 1);
        System.out.println(p.name);
        dropSize1 = p.dropSize;
        dropSize2 = p.dropSize2;
        photoDelay = p.photoDelay;
        //model.put("size1", p.dropSize);
        getComController(model).changeDropSize(p.dropSize + "");
        //model.put("size2", p.dropSize2);
        getComController(model).changeSecondDropSize(p.dropSize2 + "");
        //model.put("delay", p.dropDelay);
        getComController(model).changePhotoDelay(p.photoDelay + "");
        constatData(model);
        return "arduino";
    }

    @RequestMapping(value = "/arduino/savePreset/{name}_{size1}_{size2}_{delay}", params = {"name", "size1", "size2", "delay"})
    public String savePreset(@RequestParam String name, @RequestParam String size1, @RequestParam String size2, @RequestParam String delay, ModelMap model) {
        System.out.println("Save preset");
        console = "";
        Preset p = new Preset();
        p.name = name;
        p.photoDelay = Integer.parseInt(delay);
        p.dropSize = Integer.parseInt(size1);
        p.dropSize2 = Integer.parseInt(size2);
        System.out.println("Adding preset " + p.name + " " + p.dropSize + " " + p.dropSize2 + " " + p.photoDelay);
        ComController.presets.add(p);
        ComController.savePreset();
        updateConsole(model);
        constatData(model);
        return "arduino";
    }

    private void updateConsole(ModelMap model) {
        while (!console.contains("/end/")) {
            console = getComController(model).getSerial().consoleBuffer;
            //System.out.println("size = " + console.length() + " so still waiting");
        }
        console = getComController(model).getSerial().consoleBuffer;
        System.out.println("\n" + getComController(model).getSerial().consoleBuffer);
        model.put("console", console);
        //System.out.println("CONSOLE:\n" + console);
        getComController(model).getSerial().consoleBuffer = "";
    }
}
