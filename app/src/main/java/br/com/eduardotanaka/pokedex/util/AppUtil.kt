package br.com.eduardotanaka.pokedex.util

import android.os.Looper

fun onMainThread() = Looper.myLooper() == Looper.getMainLooper()
