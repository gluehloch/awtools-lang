/*
 * $Id: ResourceKey.java 2332 2010-07-31 13:01:17Z andrewinkler $
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

/**
 * Beschreibt ein ResourceBundle.
 * 
 * @author  $Author: andrewinkler $
 * @version $Revision: 2332 $ $Date: 2010-07-31 15:01:17 +0200 (Sa, 31 Jul 2010) $
 */
public interface ResourceKey {

    /**
     * Der Schlüssel für einen Eintrag in einem Resource-Bundles .
     *
     * @return Der Schlüssel für einen Eintrag in einem Resource-Bundle.
     */
    public String getBundleKey();

    /**
     * Der Name eines Resource-Bundles.
     *
     * @return Name eines Resource-Bundles. 
     */
    public String getBundleName();

}
