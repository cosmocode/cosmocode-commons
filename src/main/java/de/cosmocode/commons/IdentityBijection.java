package de.cosmocode.commons;

/**
 * Implementation of {@link Bijections#identity()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
enum IdentityBijection implements Bijection<Object, Object> {
    
    INSTANCE;

    @Override
    public Object apply(Object from) {
        return from;
    }

    @Override
    public Bijection<Object, Object> inverse() {
        return this;
    }
    
    @Override
    public String toString() {
        return "Bijections.identity()";
    }
    
}
