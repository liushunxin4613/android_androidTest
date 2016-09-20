package adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import base.adapter.BaseRootAdapter;

public class CustomSpinnerAdapter extends BaseRootAdapter<String> implements SpinnerAdapter{
	
	private List<DataSetObserver> observerList = new ArrayList<DataSetObserver>();

	private int textviewId;
	
	public CustomSpinnerAdapter(Context context, List<String> data, int resource,int textviewId) {
		super(context, data, resource);
		this.textviewId = textviewId;
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
			holder.textView = (TextView) view.findViewById(textviewId);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();		
		}
		holder.textView.setText(data.get(position));
		return view;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {//����������ͼ
		TextView textView = new TextView(context);
		textView.setText(data.get(position));
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(16);
		textView.setPadding(10, 10, 10, 10);
		return textView;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		if (observer == null)
			throw new NullPointerException("observer����Ϊ��");
		if (!observerList.contains(observer))
			observerList.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer == null)
			throw new NullPointerException("observer����Ϊ��");
		if (!observerList.contains(observer))
			throw new Resources.NotFoundException("observerδע��");
		observerList.remove(observer);
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public String getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
	/**
	 * ����ע�˹���
	 * @param position
	 * @return
	 */
	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	/**
	 * ����ע�˹���
	 * @return
	 */
	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	private class ViewHolder {
		TextView textView;
	}
}