package lms.foodchainR.widget;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.TableStyleData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author 李梦思
 * @version 1.0
 * @description 餐桌类型适配器
 * @createTime 2013-3-30
 * 
 */
public class TableStyleAdapter extends BaseAdapter {
	private Context context;
	private List<TableStyleData> lts;

	public TableStyleAdapter(Context context, List<TableStyleData> lts) {
		this.context = context;
		this.lts = lts;
	}

	@Override
	public int getCount() {
		return lts.size();
	}

	@Override
	public Object getItem(int position) {
		return lts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.tablestyle_item, null);
			holder = new ViewHolder();
			holder.id_txt = (TextView) view.findViewById(R.id.ts_id);
			holder.seatCount_txt = (TextView) view
					.findViewById(R.id.ts_seatcount);
			holder.tableCount_txt = (TextView) view
					.findViewById(R.id.ts_tablecount);
			view.setTag(holder);
		} else
			holder = (ViewHolder) view.getTag();
		final TableStyleData ts = lts.get(position);
		holder.id_txt.setText(ts.id);
		holder.seatCount_txt.setText(ts.seatCount + "");
		holder.tableCount_txt.setText(ts.count + "");
		return view;
	}

	class ViewHolder {
		TextView id_txt;
		TextView seatCount_txt;
		TextView tableCount_txt;
	}
}
