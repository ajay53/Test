package com.example.test

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.test.databinding.ImageWithTextBinding

class ImageWithText(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {


    private val viewBinding: ImageWithTextBinding =
        ImageWithTextBinding.inflate(LayoutInflater.from(context), this, true)
    private val txt123: AppCompatTextView = findViewById(R.id.txt)

    //
    init {
//        val inflater:LayoutInflater = LayoutInflater.from(context)
//    val viewGroup : ViewGroup = context.
//        viewBinding =
//        inflate(context, R.layout.image_with_text, null)
//        viewBinding.img.setImageResource(R.drawable.ic_launcher_foreground)
//        viewBinding.txt.text = "Go Brrrr"

        val attributes: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ImageWithText, 0, 0)
        viewBinding.txt.text = attributes.getString(R.styleable.ImageWithText_titleText)
        viewBinding.img.setImageResource(
            attributes.getResourceId(
                R.styleable.ImageWithText_imageRef,
                R.drawable.ic_launcher_foreground
            )
        )
//        var titleText:String? = typedArray.getString(R.styleable.ImageWithText_titleText)
//        var imageRef:String? = typedArray.getString(R.styleable.ImageWithText_imageRef)

        attributes.recycle()
    }

    fun setText(text: String) {
//        txt123.text = text
        viewBinding.txt.text = text
    }

    fun setImageViaID(id: Int) {
        viewBinding.img.setImageResource(id)
    }

}