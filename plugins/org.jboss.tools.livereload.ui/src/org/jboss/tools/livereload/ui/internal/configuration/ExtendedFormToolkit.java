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

package org.jboss.tools.livereload.ui.internal.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Extension of the {@link FormToolkit} to provide an easy way to create {@link Combo}s
 * @author xcoulon
 *
 */
public class ExtendedFormToolkit extends FormToolkit {

	public ExtendedFormToolkit(Display display) {
		super(display);
	}

	
	public Combo createCombo(final Composite parent, final int style, final String[] items, final String selectedItem) {
		Combo combo = new Combo(parent, style | SWT.FLAT);
		if(items != null && selectedItem != null) {
			final List<String> allItems = Arrays.asList(items);
			if(allItems.contains(selectedItem)) {
				combo.setItems(items);
				combo.select(allItems.indexOf(selectedItem));
			} else {
				final List<String> completedItems = new ArrayList<String>(allItems);
				completedItems.add(selectedItem);
				combo.setItems(completedItems.toArray(new String[completedItems.size()]));
				combo.select(completedItems.size() - 1);
			}
		}
		adapt(combo, true, true);
		return combo;
	}
}
