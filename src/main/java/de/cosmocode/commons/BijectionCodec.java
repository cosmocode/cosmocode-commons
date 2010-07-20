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
 * Implementation of {@link Codec#from(Bijection)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class BijectionCodec<F, T> extends Codec<F, T> {
    
    private final Bijection<F, T> bijection;
    private final Bijection<T, F> inverse;
    
    public BijectionCodec(Bijection<F, T> bijection) {
        this.bijection = Preconditions.checkNotNull(bijection, "Bijection");
        this.inverse = Preconditions.checkNotNull(bijection.inverse(), "%s.inverse()", bijection);
    }

    @Override
    public T encode(F input) {
        return bijection.apply(input);
    }

    @Override
    public F decode(T input) {
        return inverse.apply(input);
    }
    
    @Override
    public int hashCode() {
        return bijection.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof BijectionCodec<?, ?>) {
            final BijectionCodec<?, ?> other = BijectionCodec.class.cast(that);
            return bijection.equals(other.bijection);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Codec.from(" + bijection + ")";
    }
    
}
