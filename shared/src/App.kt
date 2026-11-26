package me.y9san9.ddxofflineqr

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.options.QrBallShape
import io.github.alexzhirkevich.qrose.options.QrBrush
import io.github.alexzhirkevich.qrose.options.QrFrameShape
import io.github.alexzhirkevich.qrose.options.QrLogoShape
import io.github.alexzhirkevich.qrose.options.QrPixelShape
import io.github.alexzhirkevich.qrose.options.brush
import io.github.alexzhirkevich.qrose.options.circle
import io.github.alexzhirkevich.qrose.options.roundCorners
import io.github.alexzhirkevich.qrose.options.solid
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.readBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.y9san9.ddxofflineqr.resources.Res
import me.y9san9.ddxofflineqr.resources.app_name
import me.y9san9.ddxofflineqr.resources.compose_multiplatform
import me.y9san9.ddxofflineqr.resources.detect_qr_error
import me.y9san9.ddxofflineqr.resources.pick_qr
import me.y9san9.ddxofflineqr.resources.pick_qr_description
import me.y9san9.ddxofflineqr.resources.qr_import_ok
import me.y9san9.ddxofflineqr.resources.qr_is_not_ddx
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.ncgroup.kscan.BarcodeFormat
import org.ncgroup.kscan.BarcodeResult
import org.ncgroup.kscan.scanImage
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

@Composable
@Preview
@DevelopmentEntryPoint
fun App() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val detectQrError = stringResource(Res.string.detect_qr_error)
    val qrImportOk = stringResource(Res.string.qr_import_ok)
    val qrIsNotDdx = stringResource(Res.string.qr_is_not_ddx)
    var ddxLink by remember {
        mutableStateOf(Persistence.load())
    }
    LaunchedEffect(ddxLink) {
        if (ddxLink != null) {
            Persistence.save(ddxLink!!)
        }
    }
    val launcher = rememberFilePickerLauncher(
        type = FileKitType.Image,
    ) launcher@{ image ->
        if (image == null) return@launcher
        scope.launch {
            scanImage(
                imageBytes = image.readBytes(),
                codeTypes = listOf(BarcodeFormat.FORMAT_QR_CODE),
                result = scan@{ result ->
                    if (result !is BarcodeResult.OnSuccess) {
                        scope.launch {
                            snackbarHostState.showSnackbar(detectQrError)
                        }
                        return@scan
                    }
                    ddxLink = when (
                        val result = DDXLink.parse(result.barcode.data)
                    ) {
                        is DDXLink.Parse.Fail -> {
                            scope.launch {
                                snackbarHostState.showSnackbar(qrIsNotDdx)
                            }
                            return@scan
                        }

                        is DDXLink.Parse.Ok -> result.data
                    }
                    scope.launch {
                        snackbarHostState.showSnackbar(qrImportOk)
                    }
                },
            )
        }
    }
    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(Res.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(QrCodeScanner, null)
                        }
                    },
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        launcher.launch()
                    },
                    icon = {
                        Icon(QrCodeScanner, null)
                    },
                    text = {
                        Text(stringResource(Res.string.pick_qr))
                    },
                )
            },
        ) { padding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(padding),
            ) {
                if (ddxLink == null) {
                    DDXLinkTutorial()
                } else {
                    DDXLink(ddxLink!!)
                }
            }
        }
    }
}

@Composable
fun ColumnScope.DDXLink(linkData: DDXLink.Data) {
    var link by remember {
        val instant = Clock.System.now()
        mutableStateOf(DDXLink.at(linkData, instant))
    }

    val qrCode = rememberQrCodePainter(
        data = link,
        ballShape = QrBallShape.roundCorners(radius = .25f),
        darkPixelShape = QrPixelShape.roundCorners(),
        frameShape = QrFrameShape.roundCorners(.25f),
        darkBrush = QrBrush.solid(LocalContentColor.current),
        ballBrush = QrBrush.solid(LocalContentColor.current),
        frameBrush = QrBrush.solid(LocalContentColor.current),
    )

    LaunchedEffect(Unit) {
        var lastMultipleOfFive = run {
            val instant = Clock.System.now()
            instant.epochSeconds / 5 * 5
        }
        while (true) {
            lastMultipleOfFive += 5
            val instant = Clock.System.now()
            val delay = lastMultipleOfFive - instant.epochSeconds
            if (delay <= 0) continue
            delay(delay.seconds)
            link = DDXLink.at(linkData, Clock.System.now())
        }
    }

    Text("User ID: " + linkData.userId)
    Text("Magic Hash: " + linkData.magicHash)
    Image(
        painter = qrCode,
        contentDescription = null,
        modifier = Modifier
            .weight(1f, fill = false)
            .padding(horizontal = 80.dp, vertical = 20.dp)
            .sizeIn(maxHeight = 250.dp, maxWidth = 250.dp)
            .aspectRatio(1f)
            .fillMaxSize(),
    )
}

@Composable
fun ColumnScope.DDXLinkTutorial() {
    Icon(
        imageVector = QrCodeScanner,
        contentDescription = null,
        modifier = Modifier
            .weight(1f, fill = false)
            .sizeIn(maxHeight = 100.dp, maxWidth = 100.dp)
            .aspectRatio(1f)
            .fillMaxSize(),
    )
    Text(
        text = stringResource(Res.string.pick_qr_description),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(24.dp),
    )
}
