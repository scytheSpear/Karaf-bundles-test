package com.testing.numberservicetest;

import com.testing.numberservice.NumService;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

@Command(scope = "numService", name = "int", description = "add or minus two int")
@Service
public class IntFeature implements Action {

    @Option(name = "-o", aliases = {"--option"}, description = "An option select add(a) or minus(m)", required = true, multiValued = false)
    private String option;

    @Argument(index = 0, name = "int a", description = "int a for calculation", required = true, multiValued = false)
    private int inta;

    @Argument(index = 1, name = "int b", description = "int b for calculation", required = true, multiValued = false)
    private int intb;

    
    
    @Reference 
    private NumService nservice; 
    
    @Override
    public Object execute() throws Exception {

        
//        ServiceMethod s = new ServiceMethod();
//        NumService nservice = s.getNumService();

        if (nservice != null) {

            System.out.println("Executing int calculate feature");
            System.out.println("Option: " + option);
            System.out.println("int a: " + inta);
            System.out.println("int b: " + intb);
            if ("a".equals(option)) {
                System.out.println("Add two int: ");
                System.out.println("result of int a: " + inta + " add int b: " + intb + " is " + nservice.testadd(inta, intb));
            } else if ("m".equals(option)) {
                System.out.println("Minus two int: ");
                System.out.println("result of int a: " + inta + " minus int b: " + intb + " is " + nservice.testminus(inta, intb));

            } else {
                System.out.println("wrong option input, should be \" a \" for add or \" m \" for minus ");
            }

        } else {
            System.out.println("can not find service numberservice check service is up or not");
        }

//        s.ungetNumService();
        return null;
    }
}
