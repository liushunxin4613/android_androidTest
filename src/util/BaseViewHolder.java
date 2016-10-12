package util;

import android.view.View;

public abstract class BaseViewHolder {
	
//	private Context context;
//	private int resource;
//	
//	public BaseViewHolder(Context context, int resource) {
//		this.context = context;
//		this.resource = resource;
//	}
//	
//	public View getView(View view, ViewGroup parent){
//		if (view == null) {
//			LayoutInflater inflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			view = inflater.inflate(resource, parent, false);
//			findViewById(view);
//			view.setTag(this);
//		}
//	}

	protected abstract void findViewById(View view);
	
}
