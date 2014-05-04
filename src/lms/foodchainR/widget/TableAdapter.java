package lms.foodchainR.widget;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.SeatData;
import lms.foodchainR.data.TableData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TableAdapter extends BaseAdapter {
	private Context context;
	private List<TableData> lt;

	public TableAdapter(Context context, List<TableData> lt) {
		this.context = context;
		this.lt = lt;
	}

	@Override
	public int getCount() {
		return lt.size();
	}

	@Override
	public Object getItem(int position) {
		return lt.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		TableData t = lt.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.table_list_item, null);
			holder = new ViewHolder();
			holder.id = (TextView) view.findViewById(R.id.tli_table_id);
			holder.icon = (ImageView) view.findViewById(R.id.tli_table_icon);
			holder.seat = (MyGridView) view.findViewById(R.id.tli_seat_grid);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.id.setText(t.tableId);
		SeatAdapter sa = new SeatAdapter(context, t.getSeat());
		holder.seat.setAdapter(sa);
		holder.seat.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SeatData data = (SeatData) parent.getAdapter()
						.getItem(position);
				switch (data.state) {
				case SeatData.AVAILIABLE:

					break;
				case SeatData.OCCUPY:
					// TODO 跳转到顾客详情
					Toast.makeText(context, "添加跳转到顾客详情功能", Toast.LENGTH_SHORT)
							.show();
					break;
				case SeatData.DIRTY:
					// TODO 打扫
					Toast.makeText(context, "添加派人打扫功能", Toast.LENGTH_SHORT)
							.show();
					break;
				default:
					break;
				}
			}
		});
		// TODO 根据状态换图片
		switch (t.state) {
		case TableData.AVAILIABLE:
			holder.icon.setImageResource(R.drawable.icon);
			break;

		default:
			break;
		}
		return view;
	}

	static class ViewHolder {
		TextView id;
		ImageView icon;
		MyGridView seat;
	}
}
