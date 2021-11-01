package com.parez.composemotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.parez.composemotion.ui.theme.ComposeMotionTheme

class MainActivity : ComponentActivity() {
	@ExperimentalMaterialApi
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ComposeMotionTheme {
				Surface(color = Color.White) {
					CollapsableToolbar()
				}
			}
		}
	}
}
