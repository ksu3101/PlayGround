package com.swkang.playground.base.helper

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.swkang.model.base.helper.AlertDialogButton
import com.swkang.model.base.helper.MessageHelper
import com.swkang.playground.R
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

class MessageHelperImpl(
    private val context: Context
) : MessageHelper {
    private var toast: Toast? = null

    override fun showToast(msgResId: Int, isLong: Boolean) {
        showToast(msgResId, null, isLong)
    }

    override fun showToast(msgStr: String, isLong: Boolean) {
        showToast(0, msgStr, isLong)
    }

    private fun showToast(msgResId: Int, msgStr: String?, isLong: Boolean) {
        this.toast?.cancel()
        val msg =
            if (msgResId == 0 && msgStr.isNullOrEmpty()) {
                throw IllegalArgumentException("message parameter has not available.")
            } else {
                if (!msgStr.isNullOrEmpty()) msgStr
                else context.getString(msgResId)
            }
        val toastLength = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        this.toast = Toast.makeText(context, msg, toastLength)
        this.toast?.show()
    }

    override fun createSimpleComfirmDialog(
        message: String,
        comfirmButtonLabel: Int
    ): Completable =
        createAlertDialogBySingleSources(
            message,
            null,
            comfirmButtonLabel,
            null
        ).flatMapCompletable { Completable.complete() }

    override fun createSimpleComfirmDialog(message: Int, comfirmButtonLabel: Int): Completable =
        createAlertDialogBySingleSources(
            context.getString(message),
            null,
            comfirmButtonLabel,
            null
        ).flatMapCompletable { Completable.complete() }

    override fun createSimpleRetryDialog(
        message: String,
        retryButtonLabel: Int
    ): Single<AlertDialogButton> =
        createAlertDialogBySingleSources(
            message,
            null,
            retryButtonLabel,
            R.string.c_no
        )

    override fun createSimpleRetryDialog(
        message: Int,
        retryButtonLabel: Int
    ): Single<AlertDialogButton> =
        createAlertDialogBySingleSources(
            context.getString(message),
            null,
            retryButtonLabel,
            R.string.c_no
        )

    override fun createAlertDialog(
        message: String,
        title: String?,
        positiveButtonLabel: Int,
        negativeButtonLabel: Int
    ): Single<AlertDialogButton> = createAlertDialogBySingleSources(
        message, title, positiveButtonLabel, negativeButtonLabel
    )

    override fun createAlertDialog(
        message: Int,
        title: Int?,
        positiveButtonLabel: Int,
        negativeButtonLabel: Int
    ): Single<AlertDialogButton> = createAlertDialogBySingleSources(
        context.getText(message),
        if (title != null) context.getText(title) else null,
        positiveButtonLabel,
        negativeButtonLabel
    )

    private fun createAlertDialogBySingleSources(
        message: CharSequence,
        title: CharSequence?,
        positiveButtonLabel: Int,
        negativeButtonLabel: Int?
    ): Single<AlertDialogButton> =
        Single.create { emitter ->
            val builder = AlertDialog.Builder(context)
            builder.setMessage(message)
            if (!title.isNullOrEmpty()) {
                builder.setTitle(title)
            }
            builder.setPositiveButton(positiveButtonLabel) { dialog, which ->
                emitter.onSuccess(AlertDialogButton.POSITIVE)
            }
            negativeButtonLabel?.let {
                builder.setNegativeButton(it) { dialog, which ->
                    emitter.onSuccess(AlertDialogButton.NEGATIVE)
                }
            }
            builder.setOnCancelListener {
                emitter.onSuccess(AlertDialogButton.NONE)
            }
            val dialog = builder.create()
            emitter.setDisposable(
                Disposable.fromRunnable { dialog.dismiss() }
            )
            dialog.show()
        }

}