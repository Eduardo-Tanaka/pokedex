package br.com.eduardotanaka.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration
import br.com.eduardotanaka.pokedex.ui.base.BaseActivity
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource
import br.com.eduardotanaka.pokedex.ui.pokemonlist.PokemonListActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainActivityViewModelImpl> { factory }
    private var adapter: MainActivityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generations = ArrayList<PokemonGeneration>()

        adapter = MainActivityAdapter(generations.toList(), this@MainActivity)
        rvPokemonGenList?.layoutManager = GridLayoutManager(this@MainActivity, 2)
        rvPokemonGenList?.adapter = adapter

        adapter?.onItemSelectedListener = object :
            MainActivityAdapter.OnItemSelectedListener {
            override fun onGenerationClicked(generation: PokemonGeneration) {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("GENERATION", generation.generation)
                startActivity(intent)
            }
        }

        viewModel.getPokemonByGeneration(1)
        viewModel.getPokemonByGeneration(2)
        viewModel.getPokemonByGeneration(3)
        viewModel.getPokemonByGeneration(4)
        viewModel.getPokemonByGeneration(5)
        viewModel.getPokemonByGeneration(6)
        viewModel.getPokemonByGeneration(7)
        viewModel.getPokemonByGeneration(8)

        viewModel.pokemonSpecies.observe(this, {
            if (it.state == StatefulResource.State.SUCCESS && it.hasData()) {
                generations.add(
                    PokemonGeneration(
                        1,
                        it.resource?.data?.pokemonGeneration?.get(0)!!.generation,
                        it.resource?.data?.pokemonGeneration?.size.toString(),
                        ""
                    )
                )

                if (generations.size == 8) {
                    loading.visibility = View.INVISIBLE
                }

                val sortedList = generations.sortedBy { g: PokemonGeneration -> g.generation }
                adapter?.updateItems(sortedList)
            }
        })
    }
}