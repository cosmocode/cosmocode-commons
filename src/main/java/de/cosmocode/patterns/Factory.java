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
package de.cosmocode.patterns;

/**
 * A Factory is a class
 * able to create instances of a specific
 * type.
 * 
 * As an alternative to traditional
 * classes with static factory methods like
 * the following:
 * <pre>
 * public static <E> createPizza(PizzaType type) {
 *     switch (type) {
 *         case NY: return new NYPizza();
 *         case CHICAGO: return new ChicagoPizza();
 *         default: throw new IllegalArgumentException(type.toString());
 *     }
 * }
 * </pre> 
 * 
 * Assuming the ficitional PizzaType is an enum,
 * you should consider making this enum implement
 * this interface. This safes you a static factory class
 * and in combination with abstract method per instance
 * implementation, this approach makes you get rid
 * of the switch statement.
 * 
 * @author schoenborn@cosmocode.de
 *
 * @param <T> the generic type this factory is able to create instances of
 */
public interface Factory<T> {

    /**
     * Creates a new instance of the generic type T.
     * 
     * @return a new instance of <T>
     */
    T create();
    
}
