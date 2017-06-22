package tick;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import org.osgi.framework.*;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator,TickService {

  // stop is flag to indicate whether the timer thread should be
  //  running or not. 
  private boolean stop = false;

  // We maintain a list of all the clients of this service that have
  //  shown an interest in being ticked at
  private List<TickListener> listeners = new Vector<TickListener>();

  /** addListener() implements the method in the service interface
       TickService. It adds a client of the service to the list of 
       clients */
    public void addListener (TickListener listener)
    {
    listeners.add (listener);
    }

  /** removeListener() implements the method in the service interface
       TickService. It removes a client of the service from the list of 
       clients */
    public void removeListener (TickListener listener)
    {
    listeners.remove (listener);
    }

  /**
    start() method gets called when running osgi:start from the console
  */
  public void start(BundleContext bundleContext) throws Exception
    {
    System.out.println ("Tick bundle started");
    
    srf=bundleContext.registerService(TickService.class,this, (Dictionary<String,?>)null);
    stop = false;
    // Start a thread that will run until the value of stop changes to
    //  false. The thread waits 5 seconds, and that calls the tick() method
    //  on every client registered with the service
    new Thread (new Runnable()
      {
      public void run()
        {
        while (!stop)
          {
          try
            {
            Thread.sleep(5000);
            System.out.println ("Tick!");
            for (TickListener listener : listeners)
              listener.tick();
            }
          catch (Exception e){}
          }
        }
      }).start();
   
    
    }
ServiceRegistration<TickService> srf;
    public void stop(BundleContext context) throws Exception {
        if(srf!=null)
            srf.unregister();
       
    System.out.println("Tick bundle stopped");
    // Set the stop flag, so that the timer thread will finish on the
    //  next tick
    stop = true;
    }
    

}
