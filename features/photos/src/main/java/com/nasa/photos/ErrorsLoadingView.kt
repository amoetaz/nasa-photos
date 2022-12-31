package com.nasa.photos

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.nasa.domain.utils.Resource
import com.nasa.presentation.dataBinding.toggleVisibility



class ErrorsLoadingView @JvmOverloads
constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ConstraintLayout(ctx, attributeSet, defStyleAttr) {

    private var type: Type
    var progressBar: ProgressBar
    var group: Group
    var textError: TextView
    var btnRetry: Button

    init {

        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


        val attributes = ctx.obtainStyledAttributes(attributeSet, R.styleable.ErrorsLoadingView)

        attributes.apply {
            type = Type.values()[getInt(R.styleable.ErrorsLoadingView_type, 0)]
        }
        attributes.recycle()

        // inflate the layout into "this" layout
        inflater.inflate(R.layout.errors_loading_view, this)
        background = ContextCompat.getDrawable(context , android.R.color.white)
        progressBar = findViewById(R.id.progress_bar)
        group = findViewById(R.id.group)
        textError = findViewById(R.id.tv_error_text)
        btnRetry = findViewById(R.id.btn_try_again)

        setType(type)
    }

    fun setVisibility(boolean: Boolean) {
        toggleVisibility(boolean)
    }

    fun setType(type: Type) {
        toggleVisibility(true)
        when (type) {
            Type.LOAD -> {
                progressBar.toggleVisibility(true)
                group.toggleVisibility(false)
            }
            Type.GENERAL_ERROR -> {
                textError.text = context.getString(R.string.something_went_wrong)
                progressBar.toggleVisibility(false)
                group.toggleVisibility(true)
            }
            Type.NETWORK -> {
                textError.text = context.getString(R.string.check_internet_connection)
                progressBar.toggleVisibility(false)
                group.toggleVisibility(true)
            }
            Type.NONE -> {
                toggleVisibility(false)
            }
        }
    }

    fun setButtonAction(onclick: () -> Unit) {
        btnRetry.setOnClickListener {
            onclick()
        }
    }

    enum class Type {
        LOAD, GENERAL_ERROR, NETWORK, NONE
    }
}

@BindingAdapter("ev_onTryClick")
fun ErrorsLoadingView.onTryClick(onClick: () -> Unit) {
    btnRetry.setOnClickListener {
        onClick()
    }
}

@BindingAdapter("setErrorLoading")
fun ErrorsLoadingView.setErrorLoading(status: Resource<*>?) {
    when (status) {
        is Resource.Success -> {
            setType(ErrorsLoadingView.Type.NONE)
        }
        is Resource.Default -> {
            setType(ErrorsLoadingView.Type.NONE)
        }

        is Resource.Failure -> {
            setType(ErrorsLoadingView.Type.GENERAL_ERROR)

        }
        Resource.Loading -> {
            setType(ErrorsLoadingView.Type.LOAD)
        }

        else -> { setType(ErrorsLoadingView.Type.NONE)}
    }
}