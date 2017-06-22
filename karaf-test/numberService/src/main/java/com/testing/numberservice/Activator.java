package com.testing.numberservice;

import java.util.Dictionary;
import java.util.List;
import java.util.Vector;
import org.osgi.framework.*;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator, NumService {

    private boolean stop = false;
    ServiceRegistration<NumService> srf;
    private int count;

    private List<NserviceListener> listeners = new Vector<NserviceListener>();

    public void addListener(NserviceListener listener) {
        listeners.add(listener);
    }

    public void removeListener(NserviceListener listener) {
        listeners.remove(listener);
    }

    public int testadd(int a, int b) {
        return a + b;
    }

    public int testminus(int a, int b) {
        return a - b;
    }

    public String testaddString(String a, String b) {
        return a + b;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("NumberService started");
        stop = false;

        srf = bundleContext.registerService(NumService.class, this, (Dictionary<String, ?>) null);

        new Thread(new Runnable() {
            public void run() {
                while (!stop) {
                    try {
                        Thread.sleep(20000);
                        System.out.println("Tick!");
                        for (NserviceListener listener : listeners) {
                            listener.addCount();
                        }
                        System.out.println("count: " + count);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

    }

    public void stop(BundleContext context) throws Exception {
        if (srf != null) {
            srf.unregister();
        }
        System.out.println("NumberService stopped");
        stop = true;
    }

}
