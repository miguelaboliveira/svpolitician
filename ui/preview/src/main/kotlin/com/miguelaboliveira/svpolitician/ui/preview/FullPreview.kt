package com.miguelaboliveira.svpolitician.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Night mode",
    group = "themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@FontScalePreview
@DevicePreview
public annotation class FullPreview
