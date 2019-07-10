package org.model;

import android.content.Context;

public class GlobalContext {
	private static Context context;
	public static  void setContext(Context context) {
		GlobalContext.context = context;
	}
	public static Context getContext() {
		if(context==null)
		{
			throw new RuntimeException("Context is null or not set");
		}
		return context;
	}

}
