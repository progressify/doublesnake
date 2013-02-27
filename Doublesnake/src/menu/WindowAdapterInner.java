/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

 public class WindowAdapterInner extends WindowAdapter{

        MusicHandler music;
    
        public WindowAdapterInner(MusicHandler music){
            this.music=music;
            
        }
        public void windowClosing(WindowEvent e){
               music.setLocation("home.wav");
               ((JFrame)e.getSource()).dispose();;
        }
    }
