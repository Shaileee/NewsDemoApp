package shaileedemo.project.common.base

import android.content.Context
import android.widget.Toast
import androidx.annotation.Px

@Suppress("NOTHING_TO_INLINE")
inline fun Context.toast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

@Px
@Suppress("NOTHING_TO_INLINE")
inline fun Context.dpToPx(dp: Float): Int = (dp * resources.displayMetrics.density).toInt()
