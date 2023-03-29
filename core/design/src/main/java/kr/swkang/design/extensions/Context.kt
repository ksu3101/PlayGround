package kr.swkang.design.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

/**
 *
 * @author bmo
 * @since 2023-03-29
 */

fun Context.getBitmapFromDrawable(drawableResId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableResId)
    if (drawable == null) return null
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}
