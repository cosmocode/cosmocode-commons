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

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An {@link Annotation} used to mark
 * a class as {@link Immutable}.
 * {@link Immutable}s are, by definiton,
 * threasafe. Clients of this {@link Annotation}
 * should consider using {@link ThreadSafe} as well.
 * 
 * {@link Immutable} classes need to contain
 * only final and immutable instance variables.
 * 
 * @deprecated use {@linkplain javax.annotation.concurrent.Immutable} instead
 * @author schoenborn@cosmocode.de
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Deprecated
public @interface Immutable {

}
