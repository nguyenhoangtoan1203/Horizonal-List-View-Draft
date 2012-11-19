/*
 * Copyright (c) 2001 - 2012 Sileria, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.sileria.android.bc;

import android.webkit.WebSettings;

/**
 * EclairMR1 2.1 Wrapper.  API Level - 7.
 *
 * @author Ahmed Shakil
 * @date Dec 16, 2010
 */
class EclairMR1 extends Eclair {

	/**
	 * Set whether the WebView loads a page with overview mode.
	 */
	@Override
	public void setLoadWithOverviewMode (WebSettings settings, boolean overview) {
		settings.setLoadWithOverviewMode( overview );
	}
}