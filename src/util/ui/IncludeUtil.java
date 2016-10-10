package util.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class IncludeUtil {
	
	public static View findViewById(OnClickListener listener,Window window,int rootViewId,int viewId,Integer id){
		if(window == null) 
			return null;
		View v = window.findViewById(rootViewId);
		if (id != null) {			
			v.setId(id);
		}
		v.setOnClickListener(listener);
		return v.findViewById(viewId);
	}
	
	public static View findViewById(OnClickListener listener,View rootView,int rootViewId,int viewId,Integer id){
		if(rootView == null) 
			return null;
		View v = rootView.findViewById(rootViewId);
		if (id != null) {			
			v.setId(id);
		}
		v.setOnClickListener(listener);
		return v.findViewById(viewId);
	}
	
	public static View[] findViewArrById(OnClickListener listener,View rootView,int rootViewArrId[],int viewId,Integer arrId[]){
		if(rootView == null) 
			return null;
		View v[] = new View[rootViewArrId.length];
		for (int i = 0; i < rootViewArrId.length; i++) {
			v[i] = rootView.findViewById(rootViewArrId[i]);
			if (arrId != null) {
				if (arrId[i] != null) {			
					v[i].setId(arrId[i]);
				}
			}
			v[i].setOnClickListener(listener);
		}
		return v;
	}
	
}
