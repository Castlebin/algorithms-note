package com.heller.btrace.example.script;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Export;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.OnTimer;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.Self;

@BTrace
public class Case1Script {

    @Export
    static long counter;

    /**
     * 注意 @Dutation 返回的时间是纳秒级别
     */
    @OnMethod(clazz = "com.heller.btrace.example.Case1", method = "add", location = @Location(Kind.RETURN))
    public static void run(@Self Object self, int a, int b, @Return int result, @Duration long time) {
        BTraceUtils.println("parameter: a=" + a + ", b=" + b + ", result a+b=" + result);
        BTraceUtils.println("cost time: " + time);
        counter++;
    }

    @OnTimer(1000)
    public static void run() {
        BTraceUtils.println("execute count: " + counter);
    }

}
