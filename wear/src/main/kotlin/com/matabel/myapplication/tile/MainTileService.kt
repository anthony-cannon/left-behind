package com.matabel.myapplication.tile

import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.ResourceBuilders
import androidx.wear.tiles.TileBuilders
import androidx.wear.tiles.TileService
import androidx.wear.tiles.TimelineBuilders
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.guava.future

private const val RESOURCES_VERSION = "0"

/**
 * Skeleton for a tile with no images.
 */
class MainTileService : TileService() {

    override fun onTileRequest(requestParams: RequestBuilders.TileRequest): ListenableFuture<TileBuilders.Tile> {
        return GlobalScope.future {
            val singleTileTimeline = TimelineBuilders.Timeline.Builder().addTimelineEntry(
                TimelineBuilders.TimelineEntry.Builder()
//                    .setLayout(
//                    LayoutElementBuilders.Layout.Builder().setRoot(tileLayout(this@MainTileService)).build()
//                )
                .build()
            ).build()

            TileBuilders.Tile.Builder().setResourcesVersion(RESOURCES_VERSION)
                .setTimeline(singleTileTimeline).build()
        }
    }

    override fun onResourcesRequest(requestParams: RequestBuilders.ResourcesRequest): ListenableFuture<ResourceBuilders.Resources> {
        return GlobalScope.future {
            ResourceBuilders.Resources.Builder().setVersion(RESOURCES_VERSION).build()
        }
    }
}

//private fun tileLayout(context: Context): LayoutElementBuilders.LayoutElement {
//    return PrimaryLayout.Builder(buildDeviceParameters(context.resources))
//        .setContent(
//            Text.Builder(context, "Hello World!")
//                .setTypography(Typography.TYPOGRAPHY_CAPTION1)
//                .build()
//        ).build()
//}

//@Preview(
//    device = Devices.WEAR_OS_SMALL_ROUND,
//    showSystemUi = true,
//    backgroundColor = 0xff000000,
//    showBackground = true
//)
//@Composable
//fun TilePreview() {
//    LayoutRootPreview(root = tileLayout(LocalContext.current))
//}