package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import models.Preset;

public class ComController {
    
    Serial serial;
    
    public static ArrayList<Preset> presets = new ArrayList<Preset>();
    public static String filePath = "presets.obj";
    
    public ComController() {  
        serial = new Serial();    
        Preset p = new Preset();
        p.name = "bleble";
        p.dropDelay = 10;
        p.dropSize = 10;
        p.dropSize2 = 10;
        Preset p1 = new Preset();
        p1.name = "bleble";
        p1.dropDelay = 20;
        p1.dropSize = 20;
        p1.dropSize2 = 20;
        presets.add(p);
        presets.add(p1);
        savePreset();
    }
    
    public boolean initSerial() {
        return serial.initialize();
    }
    
    public void addPort(String portNumber) {
        serial.addPort(portNumber);
    }
    
    public void changeDropSize(String input) {
        serial.sendCharacter('q');
        serial.sendInteger(input);
        serial.sendCharacter('p');
    }
    
    public void changeSecondDropSize(String input) {        
        serial.sendCharacter('r');
        serial.sendInteger(input);
        serial.sendCharacter('p');
    }
    public void changeDropDelay(String input){ 
        serial.sendCharacter('w');
        serial.sendInteger(input);
        serial.sendCharacter('p');
    }
    
    public void changePhotoDelay(String input) {
        serial.sendCharacter('e');
        serial.sendInteger(input);
        serial.sendCharacter('p');
    }
    
    public void singleDrop(){
        serial.sendCharacter('a');
    }
    
    public void twoDrops(){
        serial.sendCharacter('b');
    }
    
    public void savePreset() {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            file = new File(filePath);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(presets);
            oos.close();
            fos.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addEntryToConsole(String entry) {
        
    }
    
    public void loadPresets()  {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try {
            file = new File(filePath);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            presets = (ArrayList<Preset>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Serial getSerial() {
        return serial;
    }
    
    
    
    
}
