package br.com.eduardotanaka.pokedex.ui.pokemonlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.ui.MainActivityViewModelImpl
import br.com.eduardotanaka.pokedex.ui.base.BaseActivity
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource
import br.com.eduardotanaka.pokedex.ui.pokemondetail.PokemonDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import timber.log.Timber


class PokemonListActivity : BaseActivity() {

    private val viewModel by viewModels<MainActivityViewModelImpl> { factory }
    private var adapter: PokemonListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        val generation = intent.getIntExtra("GENERATION", 1)
        supportActionBar?.title = "${generation}º GENERATION"

        viewModel.getPokemons(generation)
        viewModel.pokemonsList.observe(this, {
            if (it.state == StatefulResource.State.SUCCESS && it.hasData()) {
                loadingList.visibility = View.INVISIBLE
                Timber.d(it.resource?.data.toString())
                adapter = PokemonListAdapter(it.resource?.data!!, this@PokemonListActivity)
                rvPokemonList?.layoutManager = GridLayoutManager(this@PokemonListActivity, 2)
                rvPokemonList?.adapter = adapter

                adapter?.onItemSelectedListener = object :
                    PokemonListAdapter.OnItemSelectedListener {
                    override fun onPokemonClicked(
                        pokemon: Pokemon,
                        options: ActivityOptionsCompat
                    ) {
                        val intent =
                            Intent(this@PokemonListActivity, PokemonDetailActivity::class.java)
                        intent.putExtra("POKEMON", pokemon)

                        startActivity(intent, options.toBundle())
                    }
                }
            }
        })
    }
}