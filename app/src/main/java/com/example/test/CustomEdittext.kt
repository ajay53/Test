package com.example.test

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat

class CustomEdittext(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        /*val attributes: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ImageWithText, 0, 0)*/
        this.background = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
    }
}