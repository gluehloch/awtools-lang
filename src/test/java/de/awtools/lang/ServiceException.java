/*
 * $Id: ServiceException.java 2332 2010-07-31 13:01:17Z andrewinkler $
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

import java.util.Locale;

/**
 * An exception that is thrown by business services if anything goes wrong while performing a
 * business process step. It holds a key of type {@link ResourceKey} along with an optional set of
 * messages arguments. This makes it possible to display the message text of the exception easily to
 * the end user in his/her own locale.
 * 
 * @author  $Author: andrewinkler $
 * @version $Revision: 2332 $ $Date: 2010-07-31 15:01:17 +0200 (Sa, 31 Jul 2010) $
 */
public class ServiceException extends Exception {

	/** Generated UID used by the serialization engine. */
	private static final long serialVersionUID = 7633973739678250467L;

	private Object[] messageArguments;
	private ResourceKey resourceKey;

	/**
	 * @param _resourceKey The resource key referencing the exception message.
	 * @param _messageArguments An optional set of message arguments that should be interpolated into
	 * the exception message.
	 */
	public ServiceException(ResourceKey _resourceKey, Object... _messageArguments) {
		this.resourceKey = _resourceKey;
		this.messageArguments = _messageArguments;
	}

	/**
	 * @param locale The locale for which the exception message should be returned.
	 * @return The exception message for the requested locale (if an appropriate resource bundle is
	 * available).
	 */
	public String getMessage(Locale locale) {
		return ResourceUtil.getMessage(resourceKey, locale, messageArguments);
	}

	/**
	 * @return The exception message for the default locale of the host platform (if an appropriate
	 * resource bundle is available).
	 */
	@Override
	public String getLocalizedMessage() {
		return getMessage(Locale.getDefault());
	}

}
