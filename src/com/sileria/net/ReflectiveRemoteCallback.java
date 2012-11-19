/*
 * Copyright (c) 2003 - 2012 Sileria, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.sileria.net;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import com.sileria.util.Log;

/**
 * ReflectiveRemoteCallback.
 *
 * @author Ahmed Shakil
 * @date 07-03-2012
 */
public class ReflectiveRemoteCallback<T> implements RemoteCallback<T> {

	protected final WeakReference<Object> target;
	protected final String successMethod;
	protected final String failureMethod;
	protected final Class<T> resultClass;

	/**
	 * Constructor
	 *
	 * @param target		instance of the target class
	 * @param successMethod method name for {@link com.sileria.net.RemoteCallback#onSuccess(Object)}
	 * @param failureMethod method name for {@link com.sileria.net.RemoteCallback#onFailure(Throwable)}
	 * @param resultClass   class type which {@linkplain com.sileria.net.RemoteCallback#onSuccess(Object)} is expecting
	 */
	public ReflectiveRemoteCallback (Object target, String successMethod, String failureMethod, Class<T> resultClass) {
		this.target         = new WeakReference<Object>( target );
		this.successMethod  = successMethod;
		this.failureMethod  = failureMethod;
		this.resultClass    = resultClass;
	}

    /**
	 * This method invokes the success method in the target class.
	 */
	public void onSuccess (T result) {
		try {
			Object target = this.target.get();
			Method method = target.getClass().getMethod( successMethod, resultClass );
			method.invoke( target, result );
		}
		catch (Exception ex) {
			Log.e( ex.getLocalizedMessage(), ex );
		}
	}

	/**
	 * This method invokes the failure method in the target class.
	 */
	public void onFailure (RemoteException e) {
		try {
			Object target = this.target.get();
			Method method = target.getClass().getMethod( failureMethod, RemoteException.class );
			method.invoke( target, e );
		}
		catch (Exception ex) {
			Log.e( ex.getLocalizedMessage(), ex );
		}
	}
}