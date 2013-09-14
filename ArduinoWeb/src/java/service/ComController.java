package service;

import javax.swing.JOptionPane;

public class ComController {
    
    Serial serial;
    
    public ComController() {  
        serial = new Serial();        
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
        
    }
    
    public void addEntryToConsole(String entry) {
        
    }
    
    public void loadPreset(int index)  {
        
    }

    public Serial getSerial() {
        return serial;
    }
    
    
    
    
}
