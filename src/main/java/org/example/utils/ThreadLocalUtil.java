package org.example.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal utils class
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    //provide a threadlocal object
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    //get data using key
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
	
    //store key-value pair
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }


    //clear ThreadLocal in case of memory leask
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
