<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>服务器查看线程信息</title>
</head>
<body>
    <pre>
        <%
            for (Map.Entry<Thread, StackTraceElement[]> stackTraces : Thread.getAllStackTraces().entrySet()) {
                Thread thread = stackTraces.getKey();
                StackTraceElement[] stack = stackTraces.getValue();
                if (thread.equals(Thread.currentThread())) {
                    continue;
                }
                out.print("\n线程: " + thread.getName() + "\n");
                for (StackTraceElement element : stack) {
                    out.print("\t" + element + "\n");
                }
            }
        %>
    </pre>
</body>
</html>