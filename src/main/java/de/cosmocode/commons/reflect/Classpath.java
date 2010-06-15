package de.cosmocode.commons.reflect;

import java.net.URL;

public interface Classpath extends Iterable<URL> {
    
    Packages restrictTo(Iterable<String> packages);
    
    Packages restrictTo(String... packages);
    
}
