package com.swkang.model.base.helper

import androidx.annotation.StringRes
import com.swkang.model.R
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

enum class AlertDialogButton {
    NONE, POSITIVE, NEGATIVE;

    fun isPositiveButton(): Boolean = this == POSITIVE
}

interface MessageHelper {

    fun showToast(
        @StringRes msgResId: Int,
        isLong: Boolean = false
    )

    fun showToast(
        msgStr: String,
        isLong: Boolean = false
    )

    fun showSnackBar(
        msgResId: Int,
        msgStr: String? = null,
        btnLabel: Int = R.string.c_confirm,
        btnListener: (() -> Unit)? = null
    )

    fun createSimpleComfirmDialog(
        message: String,
        comfirmButtonLabel: Int = R.string.c_confirm
    ): Completable

    fun createSimpleComfirmDialog(
        message: Int,
        comfirmButtonLabel: Int = R.string.c_confirm
    ): Completable

    fun createSimpleRetryDialog(
        message: String,
        retryButtonLabel: Int = R.string.c_retry
    ): Single<AlertDialogButton>

    fun createSimpleRetryDialog(
        message: Int = R.string.c_error_retry,
        retryButtonLabel: Int = R.string.c_retry
    ): Single<AlertDialogButton>

    fun createAlertDialog(
        message: String,
        title: String? = null,
        positiveButtonLabel: Int = R.string.c_yes,
        negativeButtonLabel: Int = R.string.c_no
    ): Single<AlertDialogButton>

    fun createAlertDialog(
        message: Int,
        title: Int? = null,
        positiveButtonLabel: Int = R.string.c_yes,
        negativeButtonLabel: Int = R.string.c_no
    ): Single<AlertDialogButton>

}
