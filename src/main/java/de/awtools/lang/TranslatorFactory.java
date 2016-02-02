/*
 * $Id: TranslatorFactory.java 2332 2010-07-31 13:01:17Z andrewinkler $
 * ============================================================================
 * Project gluehloch-util
 * Copyright (c) 2000-2008 by Andre Winkler. All rights reserved.
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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Verwaltet die <code>Translator</code>.
 * 
 * @author  $Author: andrewinkler $
 * @version $Revision: 2332 $ $Date: 2010-07-31 15:01:17 +0200 (Sa, 31 Jul 2010) $
 */
public class TranslatorFactory {

    private static TranslatorFactory translatorFactory;

    /**
     * Liefert die globale Singleton Instanz der TranslatorFactory.
     *
     * @return Die gloabe Instanz dieser Klasse.
     */
    public static final TranslatorFactory defaultInstance() {
        if (translatorFactory == null) {
            translatorFactory = new TranslatorFactory();
        }
        return translatorFactory;
    }

    // ------------------------------------------------------------------------

    private final Map<CacheKey, Translator> transes =
            new HashMap<CacheKey, Translator>();

    /**
     * Liefert einen <code>Translator</code> für das eingestellte
     * Default-Locale.
     *
     * @param basename Siehe in der Beschreibung zu <code>ResourceBundle</code>
     *  in der Java API.
     * @return Liefert einen <code>Translator</code>.
     */
    public Translator getTranslator(final String basename) {
        return getTranslator(basename, Locale.getDefault());
    }

    /**
     * Liefert einen <code>Translator</code>.
     *
     * @param basename Siehe in der Beschreibung zu <code>ResourceBundle</code>
     *  in der Java API.
     * @param locale Die gewünschte Sprache.
     * @return Liefert einen <code>Translator</code>.
     */
    public Translator getTranslator(final String basename, final Locale locale) {
        return getTranslator(basename, locale, this.getClass().getClassLoader());
    }

    /**
     * Liefert einen <code>Translator</code>.
     *
     * @param basename Siehe in der Beschreibung zu <code>ResourceBundle</code>
     *  in der Java API.
     * @param locale Die gewünschte Sprache.
     * @param loader Der zu verwendende <code>ClassLoader</code>.
     * @return Liefert einen <code>Translator</code>.
     */
    public Translator getTranslator(final String basename, final Locale locale,
        final ClassLoader loader) {

        CacheKey ck = new CacheKey(basename, locale, loader);
        Translator translator = transes.get(ck);
        if (translator == null) {
            translator =
                    new Translator(ResourceBundle.getBundle(basename, locale,
                        loader));
            transes.put(ck, translator);
        }

        return translator;
    }

    // ------------------------------------------------------------------------

    private class CacheKey {
        final String basename;

        final Locale locale;

        final ClassLoader loader;

        final int hashCode;

        CacheKey(final String _basename, final Locale _locale,
                final ClassLoader _loader) {
            basename = _basename;
            locale = _locale;
            loader = _loader;
            hashCode =
                    new HashCodeBuilder().append(basename)
                        .append(locale)
                        .append(loader)
                        .hashCode();
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(final Object object) {
            boolean result = false;
            if (object instanceof CacheKey) {
                CacheKey ck = (CacheKey) object;
                result =
                        new EqualsBuilder().append(basename, ck.basename)
                            .append(loader, ck.loader)
                            .append(locale, ck.locale)
                            .isEquals();
            }
            return result;
        }
    }

}
