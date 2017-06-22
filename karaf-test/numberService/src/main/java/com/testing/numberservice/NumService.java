/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testing.numberservice;

/**
 *
 * @author user
 */
public interface NumService {

    public int testadd(int a, int b);

    public int testminus(int a, int b);

    public String testaddString(String a, String b);

    public void addListener(NserviceListener listener);

    public void removeListener(NserviceListener listener);

    public int getCount();

    public void setCount(int count);
}
