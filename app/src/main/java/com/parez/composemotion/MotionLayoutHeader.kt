package com.parez.composemotion

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.MotionLayout

@OptIn(
	ExperimentalMaterialApi::class,
	ExperimentalComposeUiApi::class
)
@Composable
fun MotionLayoutHeader(progress: Float, scrollableBody: @Composable () -> Unit) {
//	val color by animateColorAsState(
//		targetValue = if (progress > 0.5f)
//			Color.White else Color.Black,
//
//		animationSpec = spring(
//			dampingRatio = Spring.DampingRatioNoBouncy,
//			stiffness = Spring.StiffnessMedium
//		),
//	)
	MotionLayout(
		start = JsonConstraintSetStart(),
		end = JsonConstraintSetEnd(),
		progress = progress,
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
			alpha = 1f - progress
		)
		Text(
			text = "Mandalorian",
			modifier = Modifier
				.layoutId("title")
				.wrapContentHeight(),
			color = motionColor("title", "textColor"),
			fontSize = motionFontSize("title", "textSize"),
			style = MaterialTheme.typography.h6,
			textAlign = TextAlign.Center
		)
		Box(
			Modifier
				.layoutId("content")
		) {
			scrollableBody()
		}
	}
}

@Composable
private fun JsonConstraintSetStart() = ConstraintSet (""" {
	poster: { 
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
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
		top: ['title', 'bottom', 16],
	}
} """ )

@Composable
private fun JsonConstraintSetEnd() = ConstraintSet (""" {
	poster: { 
		width: "spread",
		height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
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
                  
} """)

// Constraint Sets defined by using Kotlin DSL option
//private fun startConstraintSet() = ConstraintSet {
//	val poster = createRefFor("poster")
//	val title = createRefFor("title")
//	val content = createRefFor("content")
//
//	constrain(poster) {
//		width = Dimension.fillToConstraints
//		start.linkTo(parent.start)
//		end.linkTo(parent.end)
//		top.linkTo(parent.top)
//	}
//
//	constrain(title) {
//		start.linkTo(parent.start, 16.dp)
//		top.linkTo(poster.bottom, 16.dp)
//	}
//
//	constrain(content) {
//		width = Dimension.fillToConstraints
//		top.linkTo(title.bottom, 8.dp)
//		start.linkTo(parent.start)
//		end.linkTo(parent.end)
//	}
//}
//
//private fun endConstraintSet() = ConstraintSet {
//	val poster = createRefFor("poster")
//	val title = createRefFor("title")
//	val content = createRefFor("content")
//
//	constrain(poster) {
//		width = Dimension.fillToConstraints
//		height = Dimension.value(56.dp)
//		start.linkTo(parent.start)
//		end.linkTo(parent.end)
//		top.linkTo(parent.top)
//	}
//
//	constrain(title) {
//		start.linkTo(parent.start)
//		top.linkTo(parent.top, 8.dp)
//		end.linkTo(parent.end)
//		bottom.linkTo(poster.bottom)
//	}
//
//	constrain(content) {
//		width = Dimension.fillToConstraints
//		top.linkTo(poster.bottom, 8.dp)
//		start.linkTo(parent.start)
//		end.linkTo(parent.end)
//	}
//}