/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author sergi_000
 */
public class Preset implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public int dropSize = 0, dropDelay = 0, photoDelay = 0, dropSize2 = 0;
    public String name;
    
    public Preset() {
        super();
    }

    public int getDropSize() {
        return dropSize;
    }

    public void setDropSize(int dropSize) {
        this.dropSize = dropSize;
    }

    public int getDropDelay() {
        return dropDelay;
    }

    public void setDropDelay(int dropDelay) {
        this.dropDelay = dropDelay;
    }

    public int getPhotoDelay() {
        return photoDelay;
    }

    public void setPhotoDelay(int photoDelay) {
        this.photoDelay = photoDelay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
