package com.drawiin.feature_main.core.animations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.drawiin.common_ui.theme.AnimationsDurations

@ExperimentalAnimationApi
fun mainDefaultEnterAnim(): EnterTransition = slideInHorizontally(
    initialOffsetX = { 1000 },
    animationSpec = tween(AnimationsDurations.medium)
)

@ExperimentalAnimationApi
fun mainDefaultExitAnim(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { -1000 },
    animationSpec = tween(AnimationsDurations.medium)
)

@ExperimentalAnimationApi
fun mainDefaultPopEnterAnim(): EnterTransition = slideInHorizontally(
    initialOffsetX = { -1000 },
    animationSpec = tween(AnimationsDurations.medium)
)


@ExperimentalAnimationApi
fun mainDefaultPopExitAnim(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { 1000 },
    animationSpec = tween(AnimationsDurations.medium)
)
