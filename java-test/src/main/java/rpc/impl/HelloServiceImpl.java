package rpc.impl;

import rpc.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        if (name != null && name.length() != 0) {
            return "Hello, " + name;
        }
        return "Hello, World!";
    }

}
