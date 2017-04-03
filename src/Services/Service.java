/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author bprieto
 */
public class Service {

    private boolean isWifi=false;
    private boolean isSauna=false;
    private boolean isHammam=false;
    private boolean isPool=false;

    public Service() {
    }

    public boolean isIsWifi() {
        return isWifi;
    }

    public void setIsWifi(boolean isWifi) {
        this.isWifi = isWifi;
    }

    public boolean isIsSauna() {
        return isSauna;
    }

    public void setIsSauna(boolean isSauna) {
        this.isSauna = isSauna;
    }

    public boolean isIsHammam() {
        return isHammam;
    }

    public void setIsHammam(boolean isHammam) {
        this.isHammam = isHammam;
    }

    public boolean isIsPool() {
        return isPool;
    }

    public void setIsPool(boolean isPool) {
        this.isPool = isPool;
    }
    
    
    
}
