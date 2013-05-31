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

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Codec#filter(Function)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class FilteringCodec<F, T> extends Codec<F, T> {
    
    private final Codec<F, T> codec;
    private final Function<? super F, F> function;
    
    public FilteringCodec(Codec<F, T> codec, Function<? super F, F> function) {
        this.codec = Preconditions.checkNotNull(codec, "Codec");
        this.function = Preconditions.checkNotNull(function, "Function");
    }

    @Override
    public T encode(F input) {
        return codec.encode(function.apply(input));
    }

    @Override
    public F decode(T input) {
        return function.apply(codec.decode(input));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codec, function);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof FilteringCodec<?, ?>) {
            final FilteringCodec<?, ?> other = FilteringCodec.class.cast(that);
            return codec.equals(other.codec) && function.equals(other.function);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return codec + ".filter(" + function + ")";
    }
    
}
