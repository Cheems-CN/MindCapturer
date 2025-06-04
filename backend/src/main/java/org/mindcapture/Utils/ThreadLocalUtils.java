package org.mindcapture.Utils;

public class ThreadLocalUtils {
    private static final ThreadLocal t1 = new ThreadLocal();

    public static <T> T get(){
        return (T) t1.get();
    }

    public static void set(Object value){
        t1.set(value);
    }

    public static void remove(){
        t1.remove();
    }

}
