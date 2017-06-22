package com.testing.pax.service.test;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import org.osgi.framework.*;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator,NumService {

  // stop is flag to indicate whether the timer thread should be
  //  running or not. 
  private boolean stop = false;

  // We maintain a list of all the clients of this service that have
  //  shown an interest in being ticked at
//  private List<TickListener> listeners = new Vector<TickListener>();

  /** addListener() implements the method in the service interface
       NumService. It adds a client of the service to the list of 
       clients */
//    public void addListener (TickListener listener)
//    {
//    listeners.add (listener);
//    }
//
//  /** removeListener() implements the method in the service interface
//       NumService. It removes a client of the service from the list of 
//       clients */
//    public void removeListener (TickListener listener)
//    {
//    listeners.remove (listener);
//    }
    
    public int testadd(int a, int b)
    {
        int c = a+b;
        return c;
    }
    
    public String testString(String a, String b)
    {
        String c = a + b;
        return c;
    }

    ServiceRegistration<NumService> srf;
    
  public void start(BundleContext bundleContext) throws Exception
    {
    System.out.println ("bundle started");
    stop = false;
    
    srf=bundleContext.registerService(NumService.class,this, (Dictionary<String,?>)null);
    
    }

    public void stop(BundleContext context) throws Exception {
        if(srf!=null)
            srf.unregister();
       
    System.out.println("bundle stopped");
    stop = true;
    }
    

}
