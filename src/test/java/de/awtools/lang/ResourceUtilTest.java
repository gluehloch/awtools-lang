/*
 * $Id: ResourceUtilTest.java 2710 2010-12-06 15:38:07Z andrewinkler $
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Test;

/**
 * Testet die Klasse {@link ResourceUtil}.
 * 
 * @author  $Author: andrewinkler $
 * @version $Revision: 2710 $ $Date: 2010-12-06 16:38:07 +0100 (Mo, 06 Dez 2010) $
 */
public class ResourceUtilTest {

    @Test
    public void testResourceUtil() {
        assertThat(
            ResourceUtil.getMessage(ErrorMessageKey.TASK_WRONGSTATUS,
                Locale.GERMAN)).isEqualTo("Fehlermeldung");

        assertThat(
            ResourceUtil.getMessage(ErrorMessageKey.OUT_OF_MEMORY,
                Locale.GERMAN)).isEqualTo("Nicht genug Speicher vorhanden.");

        assertThat(
            ResourceUtil.getMessage(ErrorMessageKey.SYNTAX_ERROR,
                Locale.GERMAN, "100")).isEqualTo("Syntax error at line 100.");
    }

}
