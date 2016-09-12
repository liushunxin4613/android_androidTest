package adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import base.adapter.BaseRootAdapter;
import util.data.ConfigUtil.GridViewConfig;
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
		if(minWidth > GridViewConfig.MIN_WIDTH){
			view.setLayoutParams(new LayoutParams(width, minWidth));
		}else {
			view.setLayoutParams(new LayoutParams(width, GridViewConfig.MIN_WIDTH));
		}

		View leftView = view.findViewById(GridViewConfig.VIEW_LEFT);
		View bottomView = view.findViewById(GridViewConfig.VIEW_BOTTOM);

		//判定leftView和bottomView的显示和隐藏
		int index = position + 1;
		if (index % num == 1) {
			leftView.setVisibility(View.GONE);
		}
		int a = data.size() % num;
		int b = data.size() / num;
		if (a == 0) b--;
		if (index > b * num) {
			bottomView.setVisibility(View.GONE);
		}

		return view;
	}

	class ViewHolder{
		ImageView img;
		TextView tv;
	}


}
