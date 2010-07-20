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

package de.cosmocode.commons;

import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Codec#inverse()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <T> the source type
 * @param <F> the target type
 */
final class InverseCodec<T, F> extends Codec<T, F> {
    
    private final Codec<F, T> codec;
    
    public InverseCodec(Codec<F, T> codec) {
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
        return -codec.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof InverseCodec<?, ?>) {
            final InverseCodec<?, ?> other = InverseCodec.class.cast(that);
            return codec.equals(other.codec);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return codec + ".inverse()";
    }
    
}
