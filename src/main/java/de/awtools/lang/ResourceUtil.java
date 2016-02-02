/*
 * $Id: ResourceUtil.java 2332 2010-07-31 13:01:17Z andrewinkler $
 * ============================================================================
 * Project awtools-lang
 * Copyright (c) 2004-2010 by Andre Winkler. All rights reserved.
 * ============================================================================
 *          GNU LESSER GENERAL PUBLIC LICENSE
 *  TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package de.awtools.lang;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

/**
 * Eine Utility Klasse für den Umgang mit {@link ResourceUtil}.
 * 
 * @author  $Author: andrewinkler $
 * @version $Revision: 2332 $ $Date: 2010-07-31 15:01:17 +0200 (Sa, 31 Jul 2010) $
 */
public final class ResourceUtil {

    private static final TranslatorFactory TF = new TranslatorFactory();

    private ResourceUtil() {
    }

    /**
     * Transformiert einen Enum Bezeichner in einen Property-Schlüssel für
     * die Suche in einem ResourceBundle.
     * <p>
     * Beispiel: Der Aufruf
     * <code>constantToBundleKey(&quot;error.validation&quot;, &quot;NAME_REQUIRED&quot;)</code>
     * liefert das Ergebnis <code>error.validation.name.required</code>.
     * </p>
     * 
     * @param namespacePrefix Prefix für den Namensraum.
     * @param constantName Der Bezeichner eines Enums.
     * @return Schlüssel auf Prefix und Bezeichner.
     */
    public static String constantToBundleKey(final String namespacePrefix,
        final String constantName) {

        String convertedName = constantName.replaceAll("_", ".").toLowerCase();

        if (namespacePrefix != null) {
            return namespacePrefix + "." + convertedName;
        } else {
            return convertedName;
        }
    }

    /**
     * Lädt ein ResourceBundle.
     * 
     * @param bundleName Der Name des ResourceBundles.
     * @param locale Das Locale. Ist der Parameter <code>null</code>, wird
     *     das Locale aus dem Aufruf <code>Locale.getDefault()</code> verwendet.
     * @param loader Der ClassLoader. Ist dieser Parameter <code>null</code>,
     *     wird der ClassLoader der Klasse <code>ResourceManager</code>
     *     verwendet.
     * @return Das geladene ResourceBundle.
     * @throws MissingResourceException Wird geworfen, wenn das gesuchte
     *    ResourceBundle nicht geladen werden konnte.
     */
    private static Translator lookupBundle(final String bundleName,
        final Locale locale, final ClassLoader loader) {

        Translator bundle = null;
        Locale useme = locale;

        if (locale == null) {
            useme = Locale.getDefault();
        }

        if (loader != null) {
            bundle = TF.getTranslator(bundleName, useme, loader);
        } else {
            bundle = TF.getTranslator(bundleName, useme);
        }

        return bundle;
    }

    /**
     * Sucht nach einer Ersetzung anhand der übergebenen Parameter.
     * 
     * @param key Der Schlüssel der Resource.
     * @param locale Das zu verwendende Locle. Wird <code>null</code> übergeben,
     *     wird das Locale <code>Locale.getDefault()</code> verwendet.
     * @return Die Übersetzung zu dem übergebenen Schlüssel.
     */
    public static String getString(final ResourceKey key, final Locale locale) {
        return getString(key, locale, null);
    }

    /**
     * Sucht nach einer Ersetzung anhand der übergebenen Parameter.
     *
     * @param key Der Schlüssel der Resource.
     * @param locale Das zu verwendende Locle. Wird <code>null</code> übergeben,
     *     wird das Locale <code>Locale.getDefault()</code> verwendet.
     * @return Die Übersetzung zu dem übergebenen Schlüssel.
     */
    public static String getString(final ResourceKey key, final Locale locale,
        final ClassLoader loader) {

        Translator bundle =
                lookupBundle(key.getBundleName(), locale,
                    loader == null ? key.getClass().getClassLoader() : loader);
        String value = bundle.getString(key.getBundleKey());
        return value;
    }

    /**
     * Sucht nach einer Ersetzung anhand der übergebenen Parameter. Die
     * optionalen Parameter <code>arguments</code> werden im Stile der
     * Klasse {@link MessageFormat} in die Ersetzung eingearbeitet.
     * 
     * @param key Der Schlüssel der Resource.
     * @param locale Das zu verwendende Locle. Wird <code>null</code> übergeben,
     *     wird das Locale <code>Locale.getDefault()</code> verwendet.
     * @param arguments Optionaler Parameter.
     * @return Die Übersetzung zu dem übergebenen Schlüssel.
     */
    public static String getMessage(final ResourceKey key, final Locale locale,
        final Object... arguments) {

        return getMessage(key, locale, null, arguments);
    }

    /**
     * Sucht nach einer Ersetzung anhand der übergebenen Parameter. Die
     * optionalen Parameter <code>arguments</code> werden im Stile der
     * Klasse {@link MessageFormat} in die Ersetzung eingearbeitet.
     * 
     * @param key Der Schlüssel der Resource.
     * @param locale Das zu verwendende Locle. Wird <code>null</code> übergeben,
     *     wird das Locale <code>Locale.getDefault()</code> verwendet.
     * @param loader Der zu verwendende ClassLoader zur Ermittlung des
     *     <code>ResourceBundle</code>s.
     * @param arguments Optionaler Parameter.
     * @return Die Übersetzung zu dem übergebenen Schlüssel.
     */
    public static String getMessage(final ResourceKey key, final Locale locale,
        final ClassLoader loader, final Object... arguments) {

        Translator bundle =
                lookupBundle(key.getBundleName(), locale,
                    loader == null ? key.getClass().getClassLoader() : loader);
        String value = bundle.getString(key.getBundleKey());

        if (arguments != null && arguments.length > 0) {
            Object[] argumentsCopy = new Object[arguments.length];

            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] instanceof ResourceKey) {
                    argumentsCopy[i] =
                            getString((ResourceKey) arguments[i], locale,
                                loader);
                } else {
                    argumentsCopy[i] = arguments[i];
                }
            }

            String interpolated =
                    arguments != null ? MessageFormat.format(value,
                        argumentsCopy) : value;

            return interpolated;
        } else {
            return value;
        }
    }

}
