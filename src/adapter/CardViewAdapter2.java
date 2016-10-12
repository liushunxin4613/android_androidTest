package adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import base.adapter.BaseRootAdapter;
import entity.CardViewInfo1;

public class CardViewAdapter2 extends BaseRootAdapter<CardViewInfo1> {

//	private int fromArrId[];
	
	public CardViewAdapter2(Context context, List<CardViewInfo1> data,
			int resource, int fromArrId[]) {
		super(context, data, resource);
//		this.fromArrId = fromArrId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder holder = new ViewHolder(context, position);
//		holder.getView(convertView, parent);
		return null;
	}
	
//	class ViewHolder extends BaseViewHolder{
//		
//		TextView tv;
//		
//		public ViewHolder(Context context, int resource) {
//			super(context, resource);
//		}
//
//		@Override
//		protected void findViewById(View view) {
//			tv = (TextView) view.findViewById(fromArrId[0]);
//		}
//	}
	

}
