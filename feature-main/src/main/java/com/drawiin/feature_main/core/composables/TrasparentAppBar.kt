package com.drawiin.feature_main.core.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.drawiin.common_ui.theme.Size
import com.drawiin.feature_main.R

@Composable
fun TransparentAppBar(modifier: Modifier = Modifier, onLeadingClicked: () -> Unit) {
    Row(modifier, horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = onLeadingClicked, modifier = Modifier.size(Size.iconNormal)) {
            Icon(
                modifier = Modifier.size(Size.iconNormal),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.description_action_back),
                tint = MaterialTheme.colors.surface
            )
        }
    }
}