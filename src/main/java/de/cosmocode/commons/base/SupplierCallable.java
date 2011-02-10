package de.cosmocode.commons.base;

import java.util.concurrent.Callable;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

import de.cosmocode.patterns.Adapter;

/**
 * An adapter which makes {@link Supplier}s useable as
 * {@link Callable}s.
 *
 * @since 1.21
 * @author Willi Schoenborn
 * @param <T>
 */
@Adapter(Callable.class)
final class SupplierCallable<T> implements Callable<T> {

    private final Supplier<T> supplier;
    
    SupplierCallable(Supplier<T> supplier) {
        this.supplier = Preconditions.checkNotNull(supplier, "Supplier");
    }

    @Override
    public T call() throws Exception {
        return supplier.get();
    }
    
}
