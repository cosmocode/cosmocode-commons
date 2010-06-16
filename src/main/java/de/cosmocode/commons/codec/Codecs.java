/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.commons.codec;

import java.util.Arrays;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;

/**
 * Utility class for {@link Codec}s.
 *
 * @since 1.0
 * @author Willi Schoenborn
 */
public final class Codecs {
    
    private Codecs() {
        
    }
    
    public static <F, T> Codec<F, T> asCodec(final Function<? super F, ? extends T> decoder, 
        Function<? super T, ? extends F> encoder) {
        return new FunctionCodec<F, T>(decoder, encoder);
    }
    
    private static final class FunctionCodec<F, T> extends Codec<F, T> {
        
        private final Function<? super F, ? extends T> encoder;
        private final Function<? super T, ? extends F> decoder;
        
        public FunctionCodec(Function<? super F, ? extends T> encoder, Function<? super T, ? extends F> decoder) {
            this.encoder = Preconditions.checkNotNull(encoder, "Encoder");
            this.decoder = Preconditions.checkNotNull(decoder, "Decoder");
        }

        @Override
        public T encode(F input) {
            return encoder.apply(input);
        }

        @Override
        public F decode(T input) {
            return decoder.apply(input);
        }
        
        @Override
        public int hashCode() {
            return decoder.hashCode() ^ encoder.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof FunctionCodec<?, ?>) {
                final FunctionCodec<?, ?> other = (FunctionCodec<?, ?>) that;
                return decoder.equals(other.decoder) && encoder.equals(other.encoder);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "Codecs.asCodec(" + decoder + ", " + encoder + ")";
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public static <E> Codec<E, E> identity() {
        return (Codec<E, E>) IdentityCodec.INSTANCE;
    }
    
    private static final class IdentityCodec extends Codec<Object, Object> {

        public static final Codec<Object, Object> INSTANCE = new IdentityCodec();
        
        private IdentityCodec() {
            
        }
        
        @Override
        public Object decode(Object input) {
            return input;
        }

        @Override
        public Object encode(Object input) {
            return input;
        }
        
        @Override
        public String toString() {
            return "Codecs.identity()";
        }
        
    }
    
    public static <F, T> Codec<F, T> forBiMap(BiMap<F, T> map) {
        return new BiMapCodec<F, T>(map);
    }
    
    private static final class BiMapCodec<F, T> extends Codec<F, T> {
        
        private final BiMap<F, T> map;
        
        public BiMapCodec(BiMap<F, T> map) {
            this.map = Preconditions.checkNotNull(map, "Map");
        }

        @Override
        public T encode(F input) {
            final T result = map.get(input);
            Preconditions.checkArgument(result != null || map.containsKey(input), 
                "Key '%s' not present in map", input);
            return result;
        }

        @Override
        public F decode(T input) {
            final F result = map.inverse().get(input);
            Preconditions.checkArgument(result != null || map.containsValue(input), 
                "Value '%s' not present in map", input);
            return result;
        }
        
        @Override
        public int hashCode() {
            return map.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof BiMapCodec<?, ?>) {
                final BiMapCodec<?, ?> other = (BiMapCodec<?, ?>) that;
                return map.equals(other.map);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Codecs.forBiMap(" + map + ")";
        }
        
    }
    
    public static <F, T> Codec<F, T> forBiMap(BiMap<F, T> map, F defaultKey, T defaultValue) {
        return new BiMapCodecWithDefaults<F, T>(map, defaultKey, defaultValue);
    }
    
    private static final class BiMapCodecWithDefaults<F, T> extends Codec<F, T> {
        
        private final BiMap<F, T> map;
        private final F defaultKey;
        private final T defaultValue;
        
        public BiMapCodecWithDefaults(BiMap<F, T> map, F defaultKey, T defaultValue) {
            this.map = Preconditions.checkNotNull(map, "Map");
            this.defaultKey = defaultKey;
            this.defaultValue = defaultValue;
        }

        @Override
        public T encode(F input) {
            final T result = map.get(input);
            if (result != null || map.containsKey(input)) {
                return result;
            } else {
                return defaultValue;
            }
        }

        @Override
        public F decode(T input) {
            final F result = map.inverse().get(input);
            if (result != null || map.containsValue(input)) {
                return result;
            } else {
                return defaultKey;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(map, defaultKey, defaultValue);
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof BiMapCodecWithDefaults<?, ?>) {
                final BiMapCodecWithDefaults<?, ?> other = (BiMapCodecWithDefaults<?, ?>) that;
                return map.equals(other.map) &&
                    Objects.equal(defaultKey, other.defaultKey) && 
                    Objects.equal(defaultValue, other.defaultValue);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Codecs.forBiMap(" + map + ", " + defaultKey + ", " + defaultValue + ")";
        }
        
    }
    
}
