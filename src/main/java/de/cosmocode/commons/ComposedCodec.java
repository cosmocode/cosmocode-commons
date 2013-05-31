/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Codec#compose(Bijection)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> intermediate type
 * @param <S> target type
 */
final class ComposedCodec<F, T, S> extends Codec<F, S> {
    
    private final Codec<F, T> codec;
    private final Bijection<T, S> bijection;
    private final Bijection<S, T> inverse;
    
    public ComposedCodec(Codec<F, T> codec, Bijection<T, S> bijection) {
        this.codec = Preconditions.checkNotNull(codec, "Codec");
        this.bijection = Preconditions.checkNotNull(bijection, "Bijection");
        this.inverse = Preconditions.checkNotNull(bijection.inverse(), "%s.inverse()", bijection);
    }

    @Override
    public S encode(F input) {
        return bijection.apply(codec.encode(input));
    }

    @Override
    public F decode(S input) {
        return codec.decode(inverse.apply(input));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codec, bijection);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ComposedCodec<?, ?, ?>) {
            final ComposedCodec<?, ?, ?> other = ComposedCodec.class.cast(that);
            return codec.equals(other.codec) && bijection.equals(other.bijection);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return codec + ".compose(" + bijection + ")";
    }
    
}
