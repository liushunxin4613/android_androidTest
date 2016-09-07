package adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import base.adapter.BaseOrderlyAdapter;
import util.data.ConfigUtil.ItemTextViewConfig;

public class SpLVAdapter extends BaseOrderlyAdapter<Map<String, String>> {

	public SpLVAdapter(Context context, List<Map<String, String>> data, int resource) {
		super(context, data, resource);
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
			holder.tvArr = new TextView[ItemTextViewConfig.TV_ARR_ID.length];
			for (int i = 0; i < holder.tvArr.length; i++) {
				holder.tvArr[i] = (TextView) view.findViewById(ItemTextViewConfig.TV_ARR_ID[i]);
			}
			
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		for (int i = 0; i < holder.tvArr.length; i++) {
			holder.tvArr[i].setText(data.get(position).get(ItemTextViewConfig.TV_ARR[i]));
		}
		
		return view;
	}

	class ViewHolder{
		TextView tvArr[];
	}

	@Override
	public boolean isContains(Map<String, String> obj) {
		return false;
	}

}
