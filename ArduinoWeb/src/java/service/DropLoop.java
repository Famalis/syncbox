/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author sergi_000
 */
public class DropLoop implements Runnable {

    public boolean run = true;
    public boolean single = true;
    public ComController comController;
    
    public DropLoop(ComController comController) {
        super();
        this.comController = comController;
    }

    @Override
    public void run() {
        //long beforeTime = 0L;
        while (run) {
            if(single)
                comController.singleDrop();
            else {
                comController.twoDrops();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //beforeTime = System.currentTimeMillis();
        }
    }
}
