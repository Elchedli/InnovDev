/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweaks;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author shidono
 */
public class outils {
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
    
    public static Timer setInterval(Runnable runnable,int delay){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                runnable.run();
            }
        },0,delay); 
        return timer;
    }
    
}
