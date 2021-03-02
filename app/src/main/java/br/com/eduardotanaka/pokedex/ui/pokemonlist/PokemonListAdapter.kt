package br.com.eduardotanaka.pokedex.ui.pokemonlist

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import kotlinx.android.synthetic.main.layout_pokemon_card.view.*

class PokemonListAdapter(
    var pokemons: List<Pokemon>,
    val context: Context
) : RecyclerView.Adapter<PokemonListAdapter.MainViewHolder>() {

    interface OnItemSelectedListener {
        fun onPokemonClicked(pokemon: Pokemon, options: ActivityOptionsCompat)
    }

    var onItemSelectedListener: OnItemSelectedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonListAdapter.MainViewHolder {
        val rowView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_pokemon_card, parent, false)
        return MainViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.MainViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.count()
    }

    inner class MainViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {
        private lateinit var pokemon: Pokemon

        fun bind(pokemon: Pokemon) {
            this.pokemon = pokemon

            Glide.with(context).load(this.pokemon.urlImage)
                .listener(
                    GlidePalette.with(this.pokemon.urlImage)
                        .intoCallBack {
                            rowView.pokemon_card.setCardBackgroundColor(
                                it!!.getMutedColor(
                                    BitmapPalette.Profile.MUTED
                                )
                            )
                        }
                )
                .into(rowView.pokemon_card_image)
            rowView.pokemon_card_name.text = this.pokemon.name
            rowView.pokemon_card.transitionName = "pokemon_${this.pokemon.name}"

            rowView.pokemon_card.setOnClickListener {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity,
                    rowView.pokemon_card,
                    "pokemon_${pokemon.name}"
                )
                onItemSelectedListener?.onPokemonClicked(pokemon, options)
            }
        }
    }

    fun updateItems(newPokemons: List<Pokemon>) {
        pokemons = newPokemons
        notifyDataSetChanged()
    }
}
