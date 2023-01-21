package com.bojanvilic.film.ui.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bojanvilic.film.theme.AppTheme
import com.bojanvilic.film.ui.TopLevelDestinations

@Composable
fun TabRow(
    allScreens: List<TopLevelDestinations>,
    onTabSelected: (TopLevelDestinations) -> Unit,
    currentScreen: TopLevelDestinations
) {
    Surface(
        Modifier
            .height(TopBarHeight)
            .fillMaxWidth()
            .shadow(elevation = 20.dp)
    ) {
        Row(
            Modifier
                .selectableGroup()
                .fillMaxWidth()) {
            allScreens.forEach { screen ->
                Spacer(modifier = Modifier.weight(1f))
                Tab(
                    title = screen.title,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
private fun Tab(
    title: String,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colorScheme.onSurface
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .animateContentSize()
            .height(TopBarHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        if (selected) {
            Text(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                    )
                    .padding(horizontal = 32.dp),
                text = title,
                color = tabTintColor
            )
        } else {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title,
                color = tabTintColor
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TabRowPreview() {
    AppTheme {
        TabRow(TopLevelDestinations.values().toList(),
            {},
            TopLevelDestinations.All
        )
    }
}

val TopBarHeight = 56.dp
private const val InactiveTabOpacity = 0.60f
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 200
private const val TabFadeOutAnimationDuration = 100