package adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import base.adapter.BaseRootAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseRootAdapter<Map<String, Integer>> {

	private int fromArrId[];
	private String toArr[];
	
	private int num = 2;
	private int minWidth = 200;
	
	public void setParameter(int num,int minWidth){
		this.num = num;
		this.minWidth = minWidth;
	}
	
	public GridViewAdapter(Context context, List<Map<String, Integer>> data, int resource,int fromArrId[],String toArr[]) {
		super(context, data, resource);
		this.fromArrId = fromArrId;
		this.toArr = toArr;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View view = convertView;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resource, parent, false);
			holder = new ViewHolder();
			holder.img = (ImageView) view.findViewById(fromArrId[0]);
			holder.tv = (TextView) view.findViewById(fromArrId[1]);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.img.setBackgroundResource(data.get(position).get(toArr[0]));
		holder.tv.setText(data.get(position).get(toArr[1]));
		
		int width = parent.getWidth()/num;
		if(width/4*3 > minWidth){
			view.setLayoutParams(new LayoutParams(width, width/4*3));
		}else {
			view.setLayoutParams(new LayoutParams(width, minWidth));
		}
		
		return view;
	}

	class ViewHolder{
		ImageView img;
		TextView tv;
	}
	
	
}
