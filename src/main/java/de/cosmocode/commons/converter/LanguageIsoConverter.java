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

package de.cosmocode.commons.converter;

/**
 * Converts ISO 639-2 coded language Strings (3-letter) into ISO 639-1 coded language Strings (2-letter)
 * and vice-versa.
 *
 * @author Oliver Lorenz
 */
public interface LanguageIsoConverter {

    /**
     * <p> Converts an ISO 639-1 coded language code to ISO 639-2.
     * </p>
     * <p> Example: toThreeLetter("de") would result in "deu".
     * </p>
     * @param iso6391 the ISO 639-1 language code
     * @return the ISO 639-2 conversion of the parameter
     * @throws IsoConversionException if no ISO 639-2 code is known for the given ISO 639-1 code
     */
    String toThreeLetter(final String iso6391);

    /**
     * <p> Converts an ISO 639-2 coded language code to ISO 639-1.
     * </p>
     * <p> Example: toTwoLetter("deu") would result in "de".
     * </p>
     * @param iso6392 the ISO 639-2 language code
     * @return the ISO 639-1 conversion of the parameter
     * @throws IsoConversionException if no ISO 639-1 code is known for the given ISO 639-2 code
     */
    String toTwoLetter(final String iso6392);

}
