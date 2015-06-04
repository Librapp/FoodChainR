package lms.foodchainR.widget;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.CaseData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	private Context context;
	private List<CaseData> lc;

	public MenuAdapter(Context context, List<CaseData> lc) {
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
		ViewHolder holder;
		CaseData c = lc.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.menu_list_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(R.id.case_name);
			holder.price = (TextView) view.findViewById(R.id.case_price);
			holder.state = (TextView) view.findViewById(R.id.case_status);
			holder.pic = (ImageView) view.findViewById(R.id.case_pic);
			view.setTag(holder);
		} else
			holder = (ViewHolder) view.getTag();
		holder.price.setText(c.price + "元");
		holder.name.setText(c.name);

		switch (c.state) {
		case CaseData.AVILIABLE:
			holder.state.setText("热卖中");
			break;
		case CaseData.UNAVILIABLE:
			holder.state.setText("售罄");
			break;
		default:
			break;
		}

		Drawable d = Drawable.createFromPath(c.pic);
		if (d != null) {
			holder.pic.setImageDrawable(d);
		}
		return view;
	}

	class ViewHolder {
		TextView name;
		TextView price;
		TextView state;
		ImageView pic;
	}
}
