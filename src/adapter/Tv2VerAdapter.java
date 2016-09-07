package adapter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import base.adapter.BaseOrderlyAdapter;
import util.data.ConfigUtil.ItemTv2VerConfig;

public class Tv2VerAdapter extends BaseOrderlyAdapter<JSONObject> {

	public Tv2VerAdapter(Context context, List<JSONObject> data, int resource) {
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
			holder.tvArr = new TextView[ItemTv2VerConfig.TV_ARR_ID.length];
			for (int i = 0; i < holder.tvArr.length; i++) {
				holder.tvArr[i] = (TextView) view.findViewById(ItemTv2VerConfig.TV_ARR_ID[i]);
			}
			
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		for (int i = 0; i < holder.tvArr.length; i++) {
			try {
				holder.tvArr[i].setText(data.get(position).getString(ItemTv2VerConfig.TV_ARR[i]));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return view;
	}

	class ViewHolder{
		TextView tvArr[];
	}

	@Override
	public boolean isContains(JSONObject obj) {
		for (JSONObject map:data) {
			try {
				if (map.getString(ItemTv2VerConfig.KEY_ID).equals(obj.getString(ItemTv2VerConfig.KEY_ID))) {
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
