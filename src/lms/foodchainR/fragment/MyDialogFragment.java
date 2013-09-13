package lms.foodchainR.fragment;

import lms.foodchainR.R;
import lms.foodchainR.activity.CaseStyleDetailActivity;
import lms.foodchainR.dao.Case_DBHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.data.TableStyleData;
import lms.foodchainR.service.TableService;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment {
	public static final int CASESTYLE = 0;
	public static final int TABLESTYLE = 1;
	public static final int PHOTOPICK = 2;
	private static int mNum;
	public final String CODE = "A1";
	public final String SEATCOUNT = "4";
	public final String COUNT = "10";
	private boolean isNew;
	private TableStyleData tsb;
	private EditText edit1;
	private EditText edit2;
	private EditText edit3;
	private TextView text1;
	private TextView text2;

	public static MyDialogFragment caseStyleInstance() {
		MyDialogFragment f = new MyDialogFragment();
		mNum = CASESTYLE;
		return f;
	}

	public static MyDialogFragment tableStyleInstance(String code,
			int seatcount, int count) {
		MyDialogFragment f = new MyDialogFragment();
		mNum = TABLESTYLE;
		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("seatcount", seatcount);
		args.putInt("count", count);
		args.putCharSequence("code", code);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = new View(getActivity());
		switch (mNum) {
		case TABLESTYLE:
			setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme);
			v = inflater.inflate(R.layout.dialog_tablestyle, container, false);
			edit1 = (EditText) v.findViewById(R.id.code);
			edit2 = (EditText) v.findViewById(R.id.seatcount);
			edit3 = (EditText) v.findViewById(R.id.count);
			text1 = (TextView) v.findViewById(R.id.code_text);
			text2 = (TextView) v.findViewById(R.id.seatcount_text);
			String code = getArguments().getString("code");
			int seatcount = getArguments().getInt("seatcount");
			int count = getArguments().getInt("count");
			tsb = new TableStyleData(code, seatcount, count);
			if (code.equals("")) {
				isNew = true;
				edit1.setText(CODE);
				edit2.setText(SEATCOUNT);
				edit3.setText(COUNT);
			} else {
				isNew = false;
				edit1.setVisibility(View.GONE);
				text1.setText(code);
				text1.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.GONE);
				text2.setText(seatcount + "");
				text2.setVisibility(View.VISIBLE);
				edit3.setText(count + "");
			}
			v.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (isNew) {
						if (!edit1.getText().toString().trim().equals("")) {
							String id = edit1.getText().toString();
							tsb.id = id;
							if (tsb.id.length() != 2) {
								Toast.makeText(getActivity(),
										"编号必须为两位，请修改后重新保存！", Toast.LENGTH_SHORT)
										.show();
								return;
							}

							try {
								tsb.seatCount = Integer.parseInt(edit2
										.getText().toString());
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(getActivity(),
										"座位数格式不对，请修改后重新保存！", Toast.LENGTH_SHORT)
										.show();
								return;
							}

							if (tsb.seatCount > 20) {
								Toast.makeText(
										getActivity(),
										"一张桌子" + tsb.seatCount
												+ "个座位？这不科学,请改小一点",
										Toast.LENGTH_SHORT).show();
								return;
							}
						} else {
							Toast.makeText(getActivity(), "桌子类型编号不能为空！",
									Toast.LENGTH_SHORT).show();
							return;
						}

						if (TableService.getTableStyleDetail(tsb)) {
							Toast.makeText(getActivity(), "已经有这个编号了，请修改后重新保存！",
									Toast.LENGTH_SHORT).show();
							return;
						}
					}
					try {
						tsb.count = Integer
								.parseInt(edit3.getText().toString());
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getActivity(), "数量格式不对，请修改后重新保存！",
								Toast.LENGTH_SHORT).show();
						return;
					}
					if (isNew) {
						if (TableService.createTableStyle(tsb)) {
							// TODO 刷新数据
							dismiss();
						} else {
							Toast.makeText(getActivity(), "创建失败",
									Toast.LENGTH_SHORT).show();
							return;
						}
					} else {
						if (TableService.upgradeTableStyle(tsb)) {
							// TODO 刷新数据
							dismiss();
						} else {
							Toast.makeText(getActivity(), "修改失败",
									Toast.LENGTH_SHORT).show();
							return;
						}
					}
				}
			});
			v.findViewById(R.id.cancel).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							dismiss();
						}
					});
			break;
		case CASESTYLE:
			setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme);
			v = inflater.inflate(R.layout.dialog_casestyle, container, false);
			edit1 = (EditText) v.findViewById(R.id.name);
			edit1.requestFocus();
			v.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (edit1.getText().toString().trim().length() > 0) {
						CaseStyleData csd = new CaseStyleData();
						csd.name = edit1.getText().toString().trim();
						if (new Case_DBHelper(getActivity())
								.createCaseStyle(csd)) {
							new Case_DBHelper(getActivity())
									.getCaseStyleDataByName(csd);
							Intent intent = new Intent(getActivity(),
									CaseStyleDetailActivity.class);
							intent.putExtra("id", csd.id);
							intent.putExtra("name", csd.name);
							startActivity(intent);
							dismiss();
						}
					}
				}
			});
			v.findViewById(R.id.cancel).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							dismiss();
						}
					});
			break;
		case PHOTOPICK:

			break;
		default:
			break;
		}
		return v;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateDialog(savedInstanceState);
	}
}
