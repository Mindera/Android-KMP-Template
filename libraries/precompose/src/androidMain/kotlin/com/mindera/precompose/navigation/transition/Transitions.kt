package com.mindera.precompose.navigation.transition

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import moe.tlaster.precompose.navigation.transition.NavTransition

private const val delayAnimationTime = 300
private const val animationTime = 500

fun defaultTransition(
    create: EnterTransition = EnterTransition.None,
    destroy: ExitTransition = ExitTransition.None,
    resume: EnterTransition = EnterTransition.None,
    pause: ExitTransition = ExitTransition.None,
    enterZIndex: Float = 0f,
    exitZIndex: Float = 0f,
) = NavTransition(
    createTransition = create,
    destroyTransition = destroy,
    resumeTransition = resume,
    pauseTransition = pause,
    enterTargetContentZIndex = enterZIndex,
    exitTargetContentZIndex = exitZIndex
)

val NoTransition = defaultTransition()

val enterTransitionFromLeft = NavTransition(
    createTransition = slideInHorizontally(initialOffsetX = { -it }),
    destroyTransition = slideOutHorizontally(targetOffsetX = { -it }),
    pauseTransition = slideOutHorizontally(targetOffsetX = { it }),
    resumeTransition = slideInHorizontally(initialOffsetX = { it }),
    exitTargetContentZIndex = 1f,
)

val enterTransitionFromRight = NavTransition(
    createTransition = slideInHorizontally(initialOffsetX = { it }),
    destroyTransition = slideOutHorizontally(targetOffsetX = { it }),
    pauseTransition = slideOutHorizontally(targetOffsetX = { -it }),
    resumeTransition = slideInHorizontally(initialOffsetX = { -it }),
    exitTargetContentZIndex = 1f,
)

val enterTransitionFromBelow = NavTransition(
    createTransition = fadeIn() + slideInVertically(initialOffsetY = { it * 1 / 8 }),
    destroyTransition = fadeOut(),
    pauseTransition = fadeOut(),
    resumeTransition = fadeIn() + slideInVertically(initialOffsetY = { it * 1 / 8 }),
    enterTargetContentZIndex = 1.0f
)

val enterTransitionFadeIn = NavTransition(
    createTransition = fadeIn(),
    destroyTransition = fadeOut(),
    pauseTransition = fadeOut(),
    resumeTransition = fadeIn(),
    enterTargetContentZIndex = 1.0f
)

val enterTransitionFromBottom = NavTransition(
    createTransition = fadeIn() + slideInVertically(
        animationSpec = tween(animationTime),
        initialOffsetY = { it }
    ),
    destroyTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = animationTime,
            delayMillis = delayAnimationTime
        )
    ) + slideOutVertically(
        animationSpec = tween(
            durationMillis = animationTime,
            delayMillis = delayAnimationTime
        ),
        targetOffsetY = { it }
    ),
    pauseTransition = fadeOut(
        animationSpec = tween(
            durationMillis = animationTime,
            delayMillis = delayAnimationTime
        ),
        targetAlpha = 1.1f
    ),
    resumeTransition = fadeIn(
        animationSpec = tween(
            durationMillis = animationTime,
            delayMillis = delayAnimationTime
        ),
        initialAlpha = 1.1f
    ),
    enterTargetContentZIndex = 0.0f,
    exitTargetContentZIndex = 1.0f
)

private const val DELAY = 500
val enterTransitionFromRightShortlyDelayed = NavTransition(
    createTransition = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(
            delayMillis = DELAY
        )
    ),
    destroyTransition = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(
            delayMillis = DELAY
        )
    ),
    pauseTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(
            delayMillis = DELAY
        )
    ),
    resumeTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(
            delayMillis = DELAY
        )
    ),
    exitTargetContentZIndex = 1f,
)
