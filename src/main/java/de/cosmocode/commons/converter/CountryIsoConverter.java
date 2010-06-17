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
 * Converts ISO 3166 coded Strings from alpha-2 to alpha-3 and back.
 *
 * @since 1.8
 * @author Oliver Lorenz
 */
public interface CountryIsoConverter {

    /**
     * <p> Converts an ISO 3166 alpha-2 country code to ISO 3166 alpha-3.
     * Example: toAlpha3("DE") would result in "DEU".
     * </p>
     * 
     * @since 1.8
     * @param iso3166Alpha2 the ISO 3166 alpha-2 country code
     * @return the ISO 3166 alpha-3 conversion of the parameter
     * @throws IsoConversionException if no alpha-3 code is known for the given alpha-2 code
     */
    String toAlpha3(final String iso3166Alpha2);

    /**
     * <p> Converts an ISO 3166 alpha-3 country code to ISO 3166 alpha-2.
     * Example: toAlpha2("DEU") would result in "DE".
     * </p>
     * 
     * @since 1.8
     * @param iso3166Alpha3 the ISO 3166 alpha-3 country code
     * @return the ISO 3166 alpha-2 conversion of the parameter
     * @throws IsoConversionException if no alpha-2 code is known for the given alpha-3 code
     */
    String toAlpha2(final String iso3166Alpha3);

}
