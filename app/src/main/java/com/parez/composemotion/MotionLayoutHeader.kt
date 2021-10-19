package com.parez.composemotion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import org.intellij.lang.annotations.Language

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MotionLayoutHeader(swipingState: SwipeableState<States>, scrollableBody: @Composable () -> Unit) {
	MotionLayout(
		start = ConstraintSet(MOTION_SCENE_START),
		end = ConstraintSet(MOTION_SCENE_END),
		progress = if (swipingState.progress.to == States.COLLAPSED) swipingState.progress.fraction else 1f - swipingState.progress.fraction,
		modifier = Modifier
			.fillMaxWidth()
	) {
		Image(
			painter = painterResource(id = R.drawable.poster),
			contentDescription = "poster",
			modifier = Modifier
				.layoutId("poster")
				.background(MaterialTheme.colors.primary),
			contentScale = ContentScale.FillWidth,
			alpha = if (swipingState.progress.to == States.EXPANDED) swipingState.progress.fraction else 1f - swipingState.progress.fraction
		)
		Text(
			text = "Mandalorian",
			modifier = Modifier
				.layoutId("text")
				.wrapContentHeight(),
			color = motionColor("text", "textColor"),
			fontSize = motionProperties("text").value.fontSize("textSize"),
			style = MaterialTheme.typography.h6,
			textAlign = TextAlign.Center
		)
		Box(
			Modifier
				.wrapContentHeight()
				.fillMaxWidth()
				.layoutId("content")
		) {
			scrollableBody()
		}
	}
}

@Language("json5")
private const val MOTION_SCENE_START: String = """ {
	poster: { 
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0]
	},
	text: {
		top: ['poster', 'bottom', 16],
		start: ['parent', 'start', 16],
		custom: {
			textColor: "#000000", 
			textSize: 40
		}
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['text', 'bottom', 16],
	}
} """

@Language("json5")
private const val MOTION_SCENE_END: String = """ {
	poster: { 
		width: "spread",
		height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	text: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0], 
		bottom: ['poster', 'bottom', 0],
		custom: {
			textColor: "#ffffff",
			textSize: 20
        }
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['poster', 'bottom', 0],
	}
                  
} """