/*
 * ============================================================================
 * Project awtools-lang
 * Copyright (c) 2000-2018 by Andre Winkler. All rights reserved.
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Testet die Funktionalität von Translator.
 * 
 * @author by Andre Winkler
 */
public class TranslatorTest {

    /** Der zu testende Translator. */
    private Translator translator;

    @Test
    public void testDefaultLocale() {
        Locale germany = new Locale("de", "DE");
        assertThat(germany).isEqualTo(Locale.GERMANY);

        Locale german = new Locale("de");
        assertThat(german).isEqualTo(Locale.GERMAN);
    }

    @Test
    public void testTransformationGermany() {
        TranslatorFactory tf = new TranslatorFactory();
        translator = tf.getTranslator("de.awtools.lang.TestTranslator",
            Locale.GERMANY);

        assertThat(translator.getString("test.error.fatal")).isEqualTo(
            "Systemfehler. Programm wird abgebrochen!");
        assertThat(translator.getString("test.text.1", "Andre")).isEqualTo(
            "Der Andre ist in Ordnung.");
        assertThat(translator.getString("test.text.1", "Lars")).isEqualTo(
            "Der Lars ist in Ordnung.");
        assertThat(translator.getString("test.text.2", "Andre", "der Lars"))
            .isEqualTo("Der Andre und der Lars spinnen.");
    }

    @Test
    public void testTransformationEnglish() {
        TranslatorFactory tf = new TranslatorFactory();
        translator = tf.getTranslator("de.awtools.lang.TestTranslator",
            Locale.ENGLISH);

        assertThat(translator.getString("test.error.fatal")).isEqualTo(
            "Fatal error. Applicatin will be aborted!");
        assertThat(translator.getString("test.text.1", "Andre")).isEqualTo(
            "Andre is ok.");
        assertThat(translator.getString("test.text.1", "Lars")).isEqualTo(
            "Lars is ok.");

        assertThat(translator.getString("test.text.2", "Andre", "der Lars"))
            .isEqualTo("The Andre and der Lars are silly.");
    }

    @Test
    public void testFailTransformation() {
        TranslatorFactory tf = new TranslatorFactory();
        translator = tf.getTranslator("de.awtools.lang.TestTranslator",
            Locale.GERMANY);

        boolean b = StringUtils.equals("Das alles alles Unsinn", (translator
            .getString("test.text.3", "alles")));
        assertThat(b).isFalse();
    }

}
