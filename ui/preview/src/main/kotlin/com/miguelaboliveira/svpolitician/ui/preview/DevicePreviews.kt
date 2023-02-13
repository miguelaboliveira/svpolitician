package com.miguelaboliveira.svpolitician.ui.preview

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Phone",
    group = "devices",
    device = "spec:shape=Normal,width=411,height=891,unit=dp,dpi=420"
)
@Preview(
    name = "Foldable",
    group = "devices",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480"
)
@Preview(
    name = "Tablet",
    group = "devices",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=420"
)
public annotation class DevicePreviews
