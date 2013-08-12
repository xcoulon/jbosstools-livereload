/******************************************************************************* 
 * Copyright (c) 2008 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Xavier Coulon - Initial API and implementation 
 ******************************************************************************/

package org.jboss.tools.livereload.core.internal.server.jetty;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.jboss.tools.livereload.core.internal.JBossLiveReloadCoreActivator;
import org.jboss.tools.livereload.core.internal.util.HttpUtils;
import org.jboss.tools.livereload.core.internal.util.Logger;

/**
 * @author xcoulon
 *
 */
public class LiveReloadScriptFileServlet extends HttpServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = 163695311668462503L;

	/**
	 * The defaultCharset to use when reading local files and no defaultCharset has been
	 * specified in the <code>accept<code> header of the incoming request.
	 */
	private final Charset defaultCharset;
	
	public LiveReloadScriptFileServlet(final Charset defaultCharset) {
		this.defaultCharset = defaultCharset;
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		Logger.info("Serving embedded /livereload.js");
		final Charset charset = HttpUtils.getContentCharSet(request.getHeader("accept"), defaultCharset);
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		final InputStream scriptContent = JBossLiveReloadCoreActivator.getDefault().getResourceContent("/script/livereload.js");
		httpServletResponse.getOutputStream().write(IOUtils.toByteArray(scriptContent));
		httpServletResponse.setStatus(200);
		httpServletResponse.setContentType("text/javascript; charset=" + charset.name());
	}

}
