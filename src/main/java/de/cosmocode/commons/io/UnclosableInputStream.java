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

package de.cosmocode.commons.io;

import java.io.FilterInputStream;
import java.io.InputStream;

import com.google.common.base.Preconditions;

/**
 * An {@link InputStream} which is not closable.
 *
 * @author Willi Schoenborn
 */
public final class UnclosableInputStream extends FilterInputStream {

    private final InputStream stream;
    
    public UnclosableInputStream(InputStream stream) {
        super(stream);
        this.stream = Preconditions.checkNotNull(stream, "Stream");
    }

    /**
     * Returns <strong>without</strong> closing.
     */
    @Override
    public void close() {
        // we don't close
    }
    
    @Override
    public String toString() {
        return "InputStreams.asUnclosable(" + stream + ")";
    }

}
