package me.y9san9.ddxofflineqr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val QrCodeScanner: ImageVector
    get() {
        val current = _qrCodeScanner
        if (current != null) return current

        return ImageVector.Builder(
            name = "androidx.compose.material3.MaterialTheme.QrCodeScanner",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            // M2 7 V2 h5 v2 H4 v3z m0 15 v-5 h2 v3 h3 v2z m15 0 v-2 h3 v-3 h2 v5z m3 -15 V4 h-3 V2 h5 v5z m-2.5 10.5 H19 V19 h-1.5z m0 -3 H19 V16 h-1.5z M16 16 h1.5 v1.5 H16z m-1.5 1.5 H16 V19 h-1.5z M13 16 h1.5 v1.5 H13z m3 -3 h1.5 v1.5 H16z m-1.5 1.5 H16 V16 h-1.5z M13 13 h1.5 v1.5 H13z m6 -8 v6 h-6 V5z m-8 8 v6 H5 v-6z m0 -8 v6 H5 V5z M9.5 17.5 v-3 h-3 v3z m0 -8 v-3 h-3 v3z m8 0 v-3 h-3 v3z
            path(
                fill = SolidColor(Color(0xFFE3E3E3)),
            ) {
                // M 2 7
                moveTo(x = 2.0f, y = 7.0f)
                // V 2
                verticalLineTo(y = 2.0f)
                // h 5
                horizontalLineToRelative(dx = 5.0f)
                // v 2
                verticalLineToRelative(dy = 2.0f)
                // H 4
                horizontalLineTo(x = 4.0f)
                // v 3z
                verticalLineToRelative(dy = 3.0f)
                close()
                // m 0 15
                moveToRelative(dx = 0.0f, dy = 15.0f)
                // v -5
                verticalLineToRelative(dy = -5.0f)
                // h 2
                horizontalLineToRelative(dx = 2.0f)
                // v 3
                verticalLineToRelative(dy = 3.0f)
                // h 3
                horizontalLineToRelative(dx = 3.0f)
                // v 2z
                verticalLineToRelative(dy = 2.0f)
                close()
                // m 15 0
                moveToRelative(dx = 15.0f, dy = 0.0f)
                // v -2
                verticalLineToRelative(dy = -2.0f)
                // h 3
                horizontalLineToRelative(dx = 3.0f)
                // v -3
                verticalLineToRelative(dy = -3.0f)
                // h 2
                horizontalLineToRelative(dx = 2.0f)
                // v 5z
                verticalLineToRelative(dy = 5.0f)
                close()
                // m 3 -15
                moveToRelative(dx = 3.0f, dy = -15.0f)
                // V 4
                verticalLineTo(y = 4.0f)
                // h -3
                horizontalLineToRelative(dx = -3.0f)
                // V 2
                verticalLineTo(y = 2.0f)
                // h 5
                horizontalLineToRelative(dx = 5.0f)
                // v 5z
                verticalLineToRelative(dy = 5.0f)
                close()
                // m -2.5 10.5
                moveToRelative(dx = -2.5f, dy = 10.5f)
                // H 19
                horizontalLineTo(x = 19.0f)
                // V 19
                verticalLineTo(y = 19.0f)
                // h -1.5z
                horizontalLineToRelative(dx = -1.5f)
                close()
                // m 0 -3
                moveToRelative(dx = 0.0f, dy = -3.0f)
                // H 19
                horizontalLineTo(x = 19.0f)
                // V 16
                verticalLineTo(y = 16.0f)
                // h -1.5z
                horizontalLineToRelative(dx = -1.5f)
                close()
                // M 16 16
                moveTo(x = 16.0f, y = 16.0f)
                // h 1.5
                horizontalLineToRelative(dx = 1.5f)
                // v 1.5
                verticalLineToRelative(dy = 1.5f)
                // H 16z
                horizontalLineTo(x = 16.0f)
                close()
                // m -1.5 1.5
                moveToRelative(dx = -1.5f, dy = 1.5f)
                // H 16
                horizontalLineTo(x = 16.0f)
                // V 19
                verticalLineTo(y = 19.0f)
                // h -1.5z
                horizontalLineToRelative(dx = -1.5f)
                close()
                // M 13 16
                moveTo(x = 13.0f, y = 16.0f)
                // h 1.5
                horizontalLineToRelative(dx = 1.5f)
                // v 1.5
                verticalLineToRelative(dy = 1.5f)
                // H 13z
                horizontalLineTo(x = 13.0f)
                close()
                // m 3 -3
                moveToRelative(dx = 3.0f, dy = -3.0f)
                // h 1.5
                horizontalLineToRelative(dx = 1.5f)
                // v 1.5
                verticalLineToRelative(dy = 1.5f)
                // H 16z
                horizontalLineTo(x = 16.0f)
                close()
                // m -1.5 1.5
                moveToRelative(dx = -1.5f, dy = 1.5f)
                // H 16
                horizontalLineTo(x = 16.0f)
                // V 16
                verticalLineTo(y = 16.0f)
                // h -1.5z
                horizontalLineToRelative(dx = -1.5f)
                close()
                // M 13 13
                moveTo(x = 13.0f, y = 13.0f)
                // h 1.5
                horizontalLineToRelative(dx = 1.5f)
                // v 1.5
                verticalLineToRelative(dy = 1.5f)
                // H 13z
                horizontalLineTo(x = 13.0f)
                close()
                // m 6 -8
                moveToRelative(dx = 6.0f, dy = -8.0f)
                // v 6
                verticalLineToRelative(dy = 6.0f)
                // h -6
                horizontalLineToRelative(dx = -6.0f)
                // V 5z
                verticalLineTo(y = 5.0f)
                close()
                // m -8 8
                moveToRelative(dx = -8.0f, dy = 8.0f)
                // v 6
                verticalLineToRelative(dy = 6.0f)
                // H 5
                horizontalLineTo(x = 5.0f)
                // v -6z
                verticalLineToRelative(dy = -6.0f)
                close()
                // m 0 -8
                moveToRelative(dx = 0.0f, dy = -8.0f)
                // v 6
                verticalLineToRelative(dy = 6.0f)
                // H 5
                horizontalLineTo(x = 5.0f)
                // V 5z
                verticalLineTo(y = 5.0f)
                close()
                // M 9.5 17.5
                moveTo(x = 9.5f, y = 17.5f)
                // v -3
                verticalLineToRelative(dy = -3.0f)
                // h -3
                horizontalLineToRelative(dx = -3.0f)
                // v 3z
                verticalLineToRelative(dy = 3.0f)
                close()
                // m 0 -8
                moveToRelative(dx = 0.0f, dy = -8.0f)
                // v -3
                verticalLineToRelative(dy = -3.0f)
                // h -3
                horizontalLineToRelative(dx = -3.0f)
                // v 3z
                verticalLineToRelative(dy = 3.0f)
                close()
                // m 8 0
                moveToRelative(dx = 8.0f, dy = 0.0f)
                // v -3
                verticalLineToRelative(dy = -3.0f)
                // h -3
                horizontalLineToRelative(dx = -3.0f)
                // v 3z
                verticalLineToRelative(dy = 3.0f)
                close()
            }
        }.build().also { _qrCodeScanner = it }
    }

@Preview
@Composable
private fun IconPreview() {
    androidx.compose.material3.MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = QrCodeScanner,
                contentDescription = null,
                modifier = Modifier
                    .width((24.0).dp)
                    .height((24.0).dp),
            )
        }
    }
}

@Suppress("ObjectPropertyName")
private var _qrCodeScanner: ImageVector? = null
