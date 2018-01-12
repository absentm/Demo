/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package saulmm.coordinatorexamples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FlexibleSpaceExampleActivity extends AppCompatActivity
	implements AppBarLayout.OnOffsetChangedListener {
	private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
	private View mFab;
	private int mMaxScrollSize;
	private boolean mIsImageHidden;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flexible_space);

		mFab = findViewById(R.id.flexible_example_fab);

		Toolbar toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				onBackPressed();
			}
		});

		AppBarLayout appbar = (AppBarLayout) findViewById(R.id.flexible_example_appbar);
		appbar.addOnOffsetChangedListener(this);
	}

	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
		if (mMaxScrollSize == 0)
			mMaxScrollSize = appBarLayout.getTotalScrollRange();

		int currentScrollPercentage = (Math.abs(i)) * 100
			/ mMaxScrollSize;

		if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
			if (!mIsImageHidden) {
				mIsImageHidden = true;

				ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
			}
		}

		if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
			if (mIsImageHidden) {
				mIsImageHidden = false;
				ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
			}
		}
	}

	public static void start(Context c) {
		c.startActivity(new Intent(c, FlexibleSpaceExampleActivity.class));
	}
}
