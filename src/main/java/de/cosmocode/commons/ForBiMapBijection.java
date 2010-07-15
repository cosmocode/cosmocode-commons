package de.cosmocode.commons;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;

/**
 * Implementation of {@link Bijections#forBiMap(BiMap)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class ForBiMapBijection<F, T> implements Bijection<F, T> {
    
    private final BiMap<F, T> map;
    
    public ForBiMapBijection(BiMap<F, T> map) {
        this.map = Preconditions.checkNotNull(map, "Map");
    }

    @Override
    public T apply(F from) {
        final T result = map.get(from);
        Preconditions.checkArgument(result != null || map.containsKey(from), "Key '%s' not present in map", from);
        return result;
    }

    @Override
    public Bijection<T, F> inverse() {
        return new InverseBiMapBijection<T, F>(this, map.inverse());
    }
    
    @Override
    public int hashCode() {
        return map.hashCode() ^ -921210296;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ForBiMapBijection<?, ?>) {
            final ForBiMapBijection<?, ?> other = ForBiMapBijection.class.cast(that);
            return map.equals(other.map);
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Bijections.forBiMap(" + map + ")";
    }

    /**
     * Inverse implementation of {@link ForBiMapBijection}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <T> source type
     * @param <F> target type
     */
    private static final class InverseBiMapBijection<T, F> implements Bijection<T, F> {
        
        private final Bijection<F, T> original;
        private final BiMap<T, F> map;
        
        public InverseBiMapBijection(Bijection<F, T> original, BiMap<T, F> map) {
            this.original = Preconditions.checkNotNull(original, "Original");
            this.map = Preconditions.checkNotNull(map, "Map");
        }

        @Override
        public F apply(T from) {
            final F result = map.get(from);
            Preconditions.checkArgument(result != null || map.containsKey(from), "Value '%s' not present in map", from);
            return result;
        }
        
        @Override
        public Bijection<F, T> inverse() {
            return original;
        }
        
        @Override
        public int hashCode() {
            return Objects.hashCode(original, map);
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof InverseBiMapBijection<?, ?>) {
                final InverseBiMapBijection<?, ?> other = InverseBiMapBijection.class.cast(that);
                return original.equals(other.original) && map.equals(other.map);
            } else {
                return false;
            }
        }
        
        @Override
        public String toString() {
            return original + ".inverse()";
        }
        
    }
    
}
