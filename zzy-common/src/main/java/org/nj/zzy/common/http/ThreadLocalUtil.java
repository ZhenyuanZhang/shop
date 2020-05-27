package org.nj.zzy.common.http;

public class ThreadLocalUtil {
    private static ThreadLocal<Context> threadLocal = ThreadLocal.withInitial(Context::new);

    public static Context getContext() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
