package ch01.se01.se01_tcp_soa.service.impl;

import ch01.se01.se01_tcp_soa.service.SayHelloService;
import com.sun.deploy.util.StringUtils;

public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        if (name == null || StringUtils.trimWhitespace(name).length() == 0) {
            name = "World";
        }
        return "Hello, " + name;
    }

}
