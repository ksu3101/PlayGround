package com.swkang.model.base.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RxEditText constructor(
    context: Context,
    attrs: AttributeSet?
) : AppCompatEditText(context, attrs) {
    private val textWatcherRef: TextWatcher
    private val textChanged = PublishSubject.create<String>()

    init {
        textWatcherRef = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                textChanged.onNext(s?.toString() ?: "")
            }
        }
        addTextChangedListener(textWatcherRef)
    }

    fun getTextChangedListener(): Observable<String> {
        return textChanged.hide().observeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .debounce(500, TimeUnit.MILLISECONDS)
    }

    fun dispose() {
        textChanged.onComplete()
        removeTextChangedListener(textWatcherRef)
    }

    override fun onDetachedFromWindow() {
        dispose()
        super.onDetachedFromWindow()
    }

}