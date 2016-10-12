package util.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

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
	
	public static TextView[] findViewArrById(OnClickListener listener,Window window,int rootViewArrId[],int viewId,Integer arrId[]){
		if(window == null) 
			return null;
		View layout[] = new View[rootViewArrId.length];
		TextView view[] = new TextView[rootViewArrId.length];
		for (int i = 0; i < rootViewArrId.length; i++) {
			layout[i] = window.findViewById(rootViewArrId[i]);
			if (arrId != null) {
				if (arrId[i] != null) {			
					layout[i].setId(arrId[i]);
				}
			}
			layout[i].setOnClickListener(listener);
			view[i] = (TextView) layout[i].findViewById(viewId);
		}
		return view;
	}
	
	public static View[] findViewArrById(OnClickListener listener,View rootView,int rootViewArrId[],int viewId,Integer arrId[]){
		if(rootView == null) 
			return null;
		View layout[] = new View[rootViewArrId.length];
		View view[] = new View[rootViewArrId.length];
		for (int i = 0; i < rootViewArrId.length; i++) {
			layout[i] = rootView.findViewById(rootViewArrId[i]);
			if (arrId != null) {
				if (arrId[i] != null) {			
					layout[i].setId(arrId[i]);
				}
			}
			layout[i].setOnClickListener(listener);
			view[i] = layout[i].findViewById(viewId);
		}
		return view;
	}
	
}
