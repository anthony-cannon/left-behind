package com.matabel.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matabel.core.designsystem.LeftBehindTheme

@Composable
fun PreferenceSwitch(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    checked: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = title
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = description.orEmpty()
            )
        }
        Switch(
            modifier = Modifier
                .wrapContentSize()
                .weight(0.2f),
            checked = checked,
            onCheckedChange = {}
        )
    }
}

@Preview("PreferenceSwitch title checked Preview")
@Composable
fun PreferenceSwitchTitleCheckedPreview() {
    LeftBehindTheme {
        PreferenceSwitch(
            title = "Test",
            checked = true,
        ) {}
    }
}

@Preview("PreferenceSwitch longTitle description checked Preview")
@Composable
fun PreferenceSwitchLongTitleDescriptionCheckedPreview() {
    LeftBehindTheme {
        PreferenceSwitch(
            title = "hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello",
            description = "Test",
            checked = true,
        ) {}
    }
}

@Preview("PreferenceSwitch title longDescription checked Preview")
@Composable
fun PreferenceSwitchTitleLongDescriptionCheckedPreview() {
    LeftBehindTheme {
        PreferenceSwitch(
            title = "Test",
            description = "hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello",
            checked = true,
        ) {}
    }
}