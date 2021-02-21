package br.com.eduardotanaka.pokedex.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.ui.base.BaseActivity
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource
import br.com.eduardotanaka.pokedex.ui.pokemonlist.PokemonListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainActivityViewModelImpl> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getPokemonByGeneration(1)
        viewModel.pokemonSpecies.observe(this, {
            if (it.state == StatefulResource.State.SUCCESS && it.hasData()) {
                val data = it.resource?.data
                cardPokemonGeneration.setOnClickListener {
                    val intent = Intent(this, PokemonListActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}