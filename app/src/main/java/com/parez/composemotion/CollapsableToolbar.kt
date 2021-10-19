package com.parez.composemotion

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity

@ExperimentalMaterialApi
@Composable
fun CollapsableToolbar() {
	val swipingState = rememberSwipeableState(initialValue = States.EXPANDED)

	BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

		val heightInPx = with(LocalDensity.current) { maxHeight.toPx() }
		val connection = remember {
			object : NestedScrollConnection {

				override fun onPreScroll(
					available: Offset,
					source: NestedScrollSource
				): Offset {
					val delta = available.y
					return if (delta < 0) {
						swipingState.performDrag(delta).toOffset()
					} else {
						Offset.Zero
					}
				}

				override fun onPostScroll(
					consumed: Offset,
					available: Offset,
					source: NestedScrollSource
				): Offset {
					val delta = available.y
					return swipingState.performDrag(delta).toOffset()
				}

				override suspend fun onPostFling(
					consumed: Velocity,
					available: Velocity
				): Velocity {
					swipingState.performFling(velocity = available.y)
					return super.onPostFling(consumed, available)
				}

				private fun Float.toOffset() = Offset(0f, this)
			}
		}
		Box(
			modifier = Modifier
				.fillMaxSize()
				.swipeable(
					state = swipingState,
					thresholds = { _, _ -> FractionalThreshold(0.5f) },
					orientation = Orientation.Vertical,
					anchors = mapOf(
						0f to States.COLLAPSED,
						heightInPx to States.EXPANDED,
					)
				)
				.nestedScroll(connection)
		) {
			Column() {
				Text(text = swipingState.progress.fraction.toString())
				MotionLayoutHeader(swipingState = swipingState) {
					ScrollableContent()
				}
			}
		}
	}

}

enum class States {
	EXPANDED,
	COLLAPSED

}