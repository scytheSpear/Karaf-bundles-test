package com.testing.numberservicetest;

import com.testing.numberservice.NumService;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

@Command(scope = "numService", name = "string", description = "add two string")
@Service
public class StringFeature implements Action {

    @Argument(index = 0, name = "string a", description = "string a for addition", required = true, multiValued = false)
    private String stringa;

    @Argument(index = 1, name = "string b", description = "string b for addition", required = true, multiValued = false)
    private String stringb;

    @Reference
    private NumService nservice;

    @Override
    public Object execute() throws Exception {

        if (nservice != null) {

            System.out.println("Executing int calculate feature");
            System.out.println("String a: " + stringa);
            System.out.println("String b: " + stringb);
            System.out.println("result of String a: " + stringa + " add String b: " + stringb + " is " + nservice.testaddString(stringa, stringb));

        } else {
            System.out.println("can not find service numberservice check service is up or not");
        }

        return null;
    }
}
