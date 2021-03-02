package br.com.eduardotanaka.pokedex.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.data.model.entity.PokemonGeneration
import kotlinx.android.synthetic.main.layout_pokemon_gen.view.*

class MainActivityAdapter(
    var generations: List<PokemonGeneration>,
    val context: Context
) : RecyclerView.Adapter<MainActivityAdapter.MainViewHolder>() {

    interface OnItemSelectedListener {
        fun onGenerationClicked(generation: PokemonGeneration)
    }

    var onItemSelectedListener: OnItemSelectedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainActivityAdapter.MainViewHolder {
        val rowView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_pokemon_gen, parent, false)
        return MainViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: MainActivityAdapter.MainViewHolder, position: Int) {
        holder.bind(generations[position])
    }

    override fun getItemCount(): Int {
        return generations.count()
    }

    inner class MainViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {

        fun bind(generation: PokemonGeneration) {
            rowView.info_gen.text = "${generation.generation.toString()}º Generation"
            rowView.info_gen_count.text = "${generation.name} pokemons"

            rowView.cardPokemonGeneration.setOnClickListener {
                onItemSelectedListener?.onGenerationClicked(generation)
            }
        }
    }

    fun updateItems(newGenerations: List<PokemonGeneration>) {
        generations = newGenerations
        notifyDataSetChanged()
    }
}
