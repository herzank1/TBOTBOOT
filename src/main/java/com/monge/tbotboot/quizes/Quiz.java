/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.quizes;

import com.monge.tbotboot.messenger.Xupdate;



/**
 *
 * @author DeliveryExpress
 * Clase padre de todos los cuestionarios
 */


public abstract class Quiz {

    String userId;
    int step;
    boolean finalized;

    public Quiz(String userId) {
        this.userId = userId;
        this.step = 0;
    }

    abstract void execute(Xupdate xupdate);

    void next() {
        this.step += 1;
    }

   
    void goTo(int step) {
        this.step = step;
    }
   
    void back() {
        this.step -= 1;
    }

    void destroy(){
    QuizesControl.destroy(this);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }
    
    
    
  

}
