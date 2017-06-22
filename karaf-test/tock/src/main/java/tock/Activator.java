package tock;

import com.testing.numberservice.NserviceListener;
import com.testing.numberservice.NumService;
import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator, NserviceListener {
    
//    BundleContext context;
    NumService ns;

    public void start(BundleContext bundleContext) throws Exception {
        
        System.out.println("Tock bundle started");

//        this.context = bundleContext;
        
        ServiceTracker tracker = new ServiceTracker(bundleContext, NumService.class.getName(), null);
        // Start the tracker
        tracker.open();

         ns = (NumService) tracker.getService();
        // Stop the tracker, since we're done with it
        tracker.close();
  
        if (ns != null) {
            ns.addListener(this);
        } else {
            throw new Exception("Can't start tock bundle, as tick service is not running");
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
//        this.context = bundleContext;
        
        
        System.out.println("Tock bundle stopped");
        ServiceTracker tracker = new ServiceTracker(bundleContext, NumService.class.getName(), null);
        tracker.open();
        ns = (NumService) tracker.getService();
        tracker.close();
        // If the Tick service is running, then ns will be non-null, and
        //  we can remove ourself from the list of listeners on the service
        if (ns != null) {
            ns.removeListener(this);
        }
    }

    @Override
    public void addCount() {
        System.out.println("tock");
        ns.setCount(ns.getCount()+1);
    }
}
