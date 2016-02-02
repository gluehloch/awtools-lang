/*
 * $Id: Translator.java 2332 2010-07-31 13:01:17Z andrewinkler $
 * ============================================================================
 * Project awtools-lang
 * Copyright (c) 2000-2010 by Andre Winkler. All rights reserved.
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
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Liefert für einen Schlüssel einen Text. Dekoriert die Klasse
 * <code>PropertyResourceBundle</code> um ein anderes Fehlerverhalten.
 * Zusätzlich können Platzhalter in der Form <code>{0}</code> verarbeitet
 * werden.
 *
 * @author  $Author: andrewinkler $
 * @version $Revision: 2332 $ $Date: 2010-07-31 15:01:17 +0200 (Sa, 31 Jul 2010) $
 */
public final class Translator {

    /** Der private Logger der Klasse. */
    private final Logger log = LoggerFactory.getLogger(Translator.class);

    /** Das dekorierte ResourceBundle. */
    private final ResourceBundle bundle;

    /**
     * Erstellt ein ResourceBundle.
     *
     * @param value Das zu dekorierende <code>ResourceBundle</code>.
     */
    public Translator(final ResourceBundle value) {
        Validate.notNull(value);
        bundle = value;
    }

    /**
     * Liefert die Übersetzung für einen String.
     *
     * @param key Der gesuchte Schlüssel.
     * @return Die Übersetzung.
     */
    public String getString(final String key) {
        return (getString(key, new Object[0]));
    }

    /**
     * Liefert die Übersetzung für einen String. Das Array der
     * <code>replaces</code> ersetzt alle Vorkommen von {0}...{n}.
     *
     * @param key Der Schlüssel.
     * @param replaces Die Ersetzungen.
     * @return Die Übersetzung.
     */
    public String getString(final String key, final Object... replaces) {
        String text;

        try {
            text = (bundle.getString(key));
        } catch (MissingResourceException ex) {
            log.debug(ex.getMessage());
            text = key;
        }

        if (replaces != null && replaces.length > 0) {
            text = (MessageFormat.format(text, replaces));
        }

        return text;
    }

}
