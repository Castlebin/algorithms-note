package com.heller.tmf.core;

public interface ExtensionCallback<Ext, R> {

    R apply(Ext ext);

}
