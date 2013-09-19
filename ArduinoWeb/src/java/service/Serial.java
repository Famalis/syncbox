package service;

import controllers.ArduinoController;
import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serial implements SerialPortEventListener {

    private SerialPort serialPort;
    private ComController controller;
    private static ArrayList<String> PORT_NAMES;
    private InputStream input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    private ArduinoController arduinoController;
    public String consoleBuffer = "";
    public String port;

    public ArduinoController getArduinoController() {
        return arduinoController;
    }

    public void setArduinoController(ArduinoController arduinoController) {
        this.arduinoController = arduinoController;
    }

    public void addPort(String port) {
        String portName = "COM" + port;
        PORT_NAMES = new ArrayList<String>();
        PORT_NAMES.add(portName);
        for (int i = 1; i < 30; i++) {
            PORT_NAMES.add("COM" + i);
        }
    }

    public boolean initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    System.out.println("Connected to arduino on port " + portName);
                    port = portName;
                    break;
                }
            }
        }

        if (portId == null) {
            /*
             String input = JOptionPane.showInputDialog("Nie znaleziono Arduino. Proszę podać właściwy COM (np. COM1)"
             + "\nAnuluj by zamknąć program.");
             if(input==null) {
             System.exit(0);
             } else {
             PORT_NAMES[0] = input;
             initialize();
             }
             return false;
             * */
            return false;
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {

        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                int available = input.available();
                byte chunk[] = new byte[available];
                input.read(chunk, 0, available);
                String sChunk = new String(chunk);
                //ArduinoController.console+= sChunk;
                consoleBuffer += new String(chunk);
                //System.out.println(new String(chunk));
                //System.out.println("Console buffer " +consoleBuffer);
                //ArduinoController.console = consoleBuffer;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void sendCharacter(char c) {
        try {
            output.write(c);
            System.out.println("Sent character " + c);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendInteger(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            String current = s.charAt(i) + "";
            int currVal = Integer.parseInt(current);
            try {
                byte b = (byte) currVal;
                output.write(b);
                System.out.println("Sent integer value " + s);
            } catch (IOException io) {
                io.printStackTrace();
                System.exit(0);
            }
        }

    }

    public void setController(ComController cc) {
        controller = cc;
    }
}
