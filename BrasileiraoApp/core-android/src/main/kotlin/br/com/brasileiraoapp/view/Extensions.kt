package br.com.brasileiraoapp.view

import android.view.View

fun View.isVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}