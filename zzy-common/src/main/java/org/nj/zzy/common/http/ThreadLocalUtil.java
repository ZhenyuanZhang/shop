package org.nj.zzy.common.http;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
public class ThreadLocalUtil {
    private static ThreadLocal<Context> threadLocal = ThreadLocal.withInitial(Context::new);

    public static Context getContext() {
        return threadLocal.get();
    }

    static void remove() {
        threadLocal.remove();
    }
}
