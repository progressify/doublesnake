/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeRete;

/**
 *
 * @author pc
 */
public interface Appleable extends Runnable {
    public  void appleSetVariable();
    public Integer getSerp1();
    public Integer getSerp2();
    public int getApple_x();
    public int getApple_y();
    public void setVariables(int[] x, int[] y, boolean b);

    public void stop();
}
