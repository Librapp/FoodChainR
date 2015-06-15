package lms.foodchainR.activity;

import android.app.Activity;
import android.os.Bundle;
import de.greenrobot.event.EventBus;

public class MenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		EventBus.getDefault().register(this);
		super.onCreate(savedInstanceState);
	}
}
