package lms.foodchainR.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 
 * @author 姜浩
 * @description 自定义侧拉布局
 * 
 */
public class CustomRelativeLayout extends RelativeLayout {
	public static final int IS_OPENED = 4;

	public CustomRelativeLayout(Context context) {
		super(context);
		state = NONE;
	}

	public CustomRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		state = NONE;
		screenWidth = ((Activity) context).getWindow().getWindowManager()
				.getDefaultDisplay().getWidth();
		float scale = context.getResources().getDisplayMetrics().density;
		PADDINGSIZE = screenWidth - (int) (110 * scale + 0.5f);

	}

	public CustomRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		state = NONE;
		screenWidth = ((Activity) context).getWindow().getWindowManager()
				.getDefaultDisplay().getWidth();
		float scale = context.getResources().getDisplayMetrics().density;
		PADDINGSIZE = screenWidth - (int) (110 * scale + 0.5f);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	public void setBrother(View view) {
		brother = view;
		int margin = 0;
		ViewGroup.LayoutParams params = brother.getLayoutParams();
		if (params instanceof RelativeLayout.LayoutParams) {
			margin = ((RelativeLayout.LayoutParams) params).leftMargin;
		} else if (params instanceof LinearLayout.LayoutParams) {
			margin = ((LinearLayout.LayoutParams) params).leftMargin;
		} else if (params instanceof FrameLayout.LayoutParams) {
			margin = ((FrameLayout.LayoutParams) params).leftMargin;
		}
		if (margin != 0) {
			PADDINGSIZE = screenWidth - margin;
		}
	}

	private View brother;

	private int screenWidth;
	private int startX;
	private int endX;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

	}

	public void setStateByBtn() {
		if (state == NONE) {
			this.setPadding(-1 * PADDINGSIZE, 0, PADDINGSIZE, 0);
			resetFocus();
			state = HALF;
		} else if (state == HALF) {
			this.getParent().bringChildToFront(this);
			this.setPadding(0, 0, 0, 0);
			state = NONE;
		}
	}

	private float moveX;
	private float moveY;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (hasViewPager) {
			return super.onInterceptTouchEvent(ev);
		}
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (state == HALF) {
				isRecord = false;
			} else {
				isRecord = true;
			}
			startX = (int) ev.getX();
			moveX = ev.getX();
			moveY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float tempMoveX = ev.getX();
			float tempMoveY = ev.getY();
			if (hasGallery) {
				if (moveY < 300) {
					return false;
				} else {
					if (Math.abs(tempMoveX - moveX) > Math.abs(tempMoveY
							- moveY)) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				if (Math.abs(tempMoveX - moveX) > Math.abs(tempMoveY - moveY)) {
					return true;
				}
			}
			break;
		default:
			break;
		}

		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (state == HALF) {
				isRecord = false;
			} else {
				isRecord = true;
			}
			startX = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			if (isRecord) {
				endX = (int) event.getX();
				if (startX - endX > 200 && state == NONE) {
					state = PULL_TO_RELASE;
				}
				if (state == PULL_TO_RELASE) {
					if (startX - endX > PADDINGSIZE) {
						state = RELEASE_TO_BACK;
						isBack = false;
					}
					// setMargin(endX-startX);
					// changePaddingByState();

					if (!(startX < endX)) {
						this.setPadding(endX - startX, 0, startX - endX, 0);
					} else {
						isBack = true;
					}

				}
				if (state == RELEASE_TO_BACK) {
					if (startX - endX < PADDINGSIZE) {
						state = PULL_TO_RELASE;
						isBack = true;
					}
					this.setPadding(endX - startX, 0, startX - endX, 0);
					// setMargin(endX-startX);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			// Log.e(TAG, "endX=" + endX + ",startX=" + startX);
			if (state == HALF) {
				if (startX < screenWidth - PADDINGSIZE) {
					this.setPadding(0, 0, 0, 0);
					this.getParent().bringChildToFront(this);
					state = NONE;
				}
			}
			if (state == PULL_TO_RELASE) {
				if (isBack) {
					this.setPadding(0, 0, 0, 0);
					state = NONE;
				} else {
					this.setPadding(-1 * PADDINGSIZE, 0, PADDINGSIZE, 0);
					resetFocus();
					state = HALF;
				}
			}
			if (state == RELEASE_TO_BACK) {
				this.setPadding(-1 * PADDINGSIZE, 0, PADDINGSIZE, 0);
				resetFocus();
				state = HALF;
			}
			isRecord = false;
			isBack = false;
			break;
		default:
			break;
		}
		return true;
	}

	private boolean isRecord;
	private boolean isBack;
	private int state;
	private static final int NONE = 1;
	private static final int PULL_TO_RELASE = 2;
	private static final int RELEASE_TO_BACK = 3;
	private static final int HALF = 4;

	private int PADDINGSIZE;

	private void resetFocus() {
		this.getParent().bringChildToFront(brother);
	}

	private boolean hasViewPager;

	public void setHasViewPager(boolean has) {
		hasViewPager = has;
	}

	private boolean hasGallery;

	public void setHasGallery(boolean has) {
		this.hasGallery = has;
	}

	public void close() {
		if (state == NONE) {
			return;
		} else if (state == HALF) {
			this.getParent().bringChildToFront(this);
			this.setPadding(0, 0, 0, 0);
			state = NONE;
		}
	}

	/**
	 * 
	 * @return 如果返回 4表示打开状态
	 */
	public int getStatus() {
		return state;
	}

	/**
	 * 
	 * @return 如果返回 4表示打开状态
	 */
	public boolean isOpened() {
		switch (state) {
		case IS_OPENED:
			return true;
		default:
			return false;
		}
	}
}
