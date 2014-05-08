package lms.foodchainR.widget;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.BillData;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BillAdapter extends BaseAdapter {
	private Context context;
	private List<BillData> lc;

	public BillAdapter(Context context, ListView list, List<BillData> lc) {
		this.context = context;
		this.lc = lc;
	}

	@Override
	public int getCount() {
		return lc.size();
	}

	@Override
	public Object getItem(int position) {
		return lc.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		BillCache bc;
		if (view == null) {
			LayoutInflater inflate = (LayoutInflater) context
					.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			view = inflate.inflate(R.layout.bill_list_item, null);
			bc = new BillCache(view);
			view.setTag(bc);
		} else {
			bc = (BillCache) view.getTag();
		}
		BillData c = lc.get(position);
		bc.getId().setText(c.billId);
		bc.getPrice().setText(c.cost + "元");
		bc.getCreateTime().setText(c.createTime);
		if (c.state == 1) {
			bc.getPaid().setText("已付款");
		} else {
			bc.getPaid().setText("未付款");
		}

		return view;
	}

	private class BillCache {
		private TextView id;
		private TextView type;
		private TextView paid;
		private TextView address;
		private TextView createTime;
		private TextView price;
		private View v;

		public BillCache(View v) {
			this.v = v;
		}

		public TextView getId() {
			if (id == null) {
				id = (TextView) v.findViewById(R.id.bl_id);
			}
			return id;
		}

		public TextView getType() {
			if (type == null) {
				type = (TextView) v.findViewById(R.id.bl_type);
			}
			return type;
		}

		public TextView getPaid() {
			if (paid == null) {
				paid = (TextView) v.findViewById(R.id.bl_paid);
			}
			return paid;
		}

		public TextView getAddress() {
			if (address == null) {
				address = (TextView) v.findViewById(R.id.bl_address);
			}
			return address;
		}

		public TextView getPrice() {
			if (price == null) {
				price = (TextView) v.findViewById(R.id.bl_price);
			}
			return price;
		}

		public TextView getCreateTime() {
			if (createTime == null) {
				createTime = (TextView) v.findViewById(R.id.bl_createtime);
			}
			return createTime;
		}
	}
}
