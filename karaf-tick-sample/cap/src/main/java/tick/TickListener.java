/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tick;

/**
 *
 * @author user
 */

public interface TickListener
  {
  /** The tick() method is called on every registered listener every time
      the Tick service ticks */
  public void tick();
  }