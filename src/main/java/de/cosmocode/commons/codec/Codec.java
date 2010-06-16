package de.cosmocode.commons.codec;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.Bijection;

/**
 * An codec allows bidirectional conversion from one type to another.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <F> first type
 * @param <T> second type
 */
public abstract class Codec<F, T> implements Bijection<F, T> {

    /**
     * Encodes input of type F into type T. This method is usually applied lazily.
     * 
     * @since 1.0
     * @param input the input
     * @return an immutable instance of T
     * @throws NullPointerException if input is null
     * TODO exception
     */
    public abstract T encode(F input);

    /**
     * Decodes input of type T into type F. This method is usually applied lazily.
     * 
     * @since 1.0
     * @param input the input
     * @return instante of F
     * @throws NullPointerException if input is null
     * TODO exception
     */
    public abstract F decode(T input);

    @Override
    public T apply(F from) {
        return encode(from);
    }

    @Override
    public Codec<T, F> inverse() {
        // TODO reuse!
        return new FlippedCodec<T, F>(this);
    }
    
    private static final class FlippedCodec<T, F> extends Codec<T, F> {
        
        private final Codec<F, T> codec;
        
        public FlippedCodec(Codec<F, T> codec) {
            this.codec = Preconditions.checkNotNull(codec, "Codec");
        }

        @Override
        public F encode(T input) {
            return codec.decode(input);
        }

        @Override
        public T decode(F input) {
            return codec.encode(input);
        }
        
        @Override
        public Codec<F, T> inverse() {
            return codec;
        }
        
        @Override
        public int hashCode() {
            return codec.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof FlippedCodec<?, ?>) {
                final FlippedCodec<?, ?> other = FlippedCodec.class.cast(that);
                return codec.equals(other.codec);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return codec + ".flip()";
        }
        
    }
    
    public <S> Codec<F, S> compose(Codec<T, S> codec) {
        return new ComposedCodec<F, T, S>(this, codec);
    }
    
    private static final class ComposedCodec<F, T, S> extends Codec<F, S> {
        
        private final Codec<F, T> left;
        private final Codec<T, S> right;
        
        public ComposedCodec(Codec<F, T> left, Codec<T, S> right) {
            this.left = Preconditions.checkNotNull(left, "Left");
            this.right = Preconditions.checkNotNull(right, "Right");
        }
        
        @Override
        public S encode(F input) {
            return right.encode(left.encode(input));
        };

        @Override
        public F decode(S input) {
            return left.decode(right.decode(input));
        };
        
        @Override
        public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof ComposedCodec<?, ?, ?>) {
                final ComposedCodec<?, ?, ?> other = ComposedCodec.class.cast(that);
                return left.equals(other.left) && right.equals(other.right);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return left + ".compose(" + right + ")";
        }
        
    }
    
}
