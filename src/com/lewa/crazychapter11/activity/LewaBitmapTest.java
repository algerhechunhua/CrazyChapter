/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lewa.crazychapter11;

import java.io.IOException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import android.util.Log;

public class LewaBitmapTest extends Activity {
	String[] images = null;
	AssetManager assets = null;
	int currentImg = 0;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bitmap_layout);
		image = (ImageView) findViewById(R.id.assetimage);
		try {
			assets = getAssets();
			images = assets.list("");
		} catch (IOException e) {
			e.printStackTrace();
		}

		final Button btn_next = (Button) findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View sources) {
				if (currentImg >= images.length) {
					currentImg = 0;
				}
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif")) {
					currentImg++;
					if (currentImg >= images.length) {
						currentImg = 0;
						break;
					}
				}

				InputStream assetFile = null;
				try {
					assetFile = assets.open(images[currentImg++]);

					if (image != null) {
						BitmapDrawable bitmapDrawable = (BitmapDrawable) image
								.getDrawable();

						if (bitmapDrawable != null
								&& bitmapDrawable.getBitmap() != null
								&& !bitmapDrawable.getBitmap().isRecycled()) {
							bitmapDrawable.getBitmap().recycle();
							Log.i("LewaBitmapTest",
									"1111 bitmapDrawable.getBitmap().isRecycled() ="
											+ bitmapDrawable.getBitmap()
													.isRecycled());
						}

						image.setImageBitmap(BitmapFactory
								.decodeStream(assetFile));
					}
				} catch (IOException e) {
					e.printStackTrace();
					Log.i("LewaBitmapTest", "IOException aaa  !!");
				} catch (Exception e) {
					Log.i("LewaBitmapTest", "image errer here !!");
				}
			}
		});
	}
}
