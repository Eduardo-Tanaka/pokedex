package br.com.eduardotanaka.pokedex.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.ui.base.BaseActivity
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource
import timber.log.Timber

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainActivityViewModelImpl> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("MainActivity iniciando")
        viewModel.getPokemonByGeneration(1)
        viewModel.pokemonSpecies.observe(this, {
            if (it.state == StatefulResource.State.SUCCESS && it.hasData()) {
                Timber.d(it.resource?.data.toString())
            }
        })
    }
}