/**
 * palava - a java-php-bridge
 * Copyright (C) 2007-2010  CosmoCode GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.cosmocode.commons.concurrent;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

/**
 * Utility class for {@link Runnable}s.
 *
 * @author Willi Schoenborn
 */
public final class Runnables {

    private static final Joiner JOINER = Joiner.on(", ");
    
    private Runnables() {
        
    }
    
    /**
     * Chains multiple {@link Runnable}s together.
     * 
     * @param first the first Runnable
     * @param second the second Runnable
     * @param rest zero or more additional Runnables
     * @return a Runnable which runs all given Runnables in a sequence
     * @throws NullPointerException if first, second or rest is null
     */
    public static Runnable chain(final Runnable first, final Runnable second, final Runnable... rest) {
        Preconditions.checkNotNull(first, "First");
        Preconditions.checkNotNull(second, "Second");
        Preconditions.checkNotNull(rest, "Rest");
        return new Runnable() {
            
            @Override
            public void run() {
                first.run();
                second.run();
                for (Runnable runnable : rest) {
                    runnable.run();
                }
            }
            
            @Override
            public String toString() {
                return String.format("Runnables.chain(%s, %s, %s)", first, second, JOINER.join(rest));
            }
            
        };
    }

}
