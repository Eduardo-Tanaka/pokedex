package br.com.eduardotanaka.pokedex.ui.pokemondetail

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.data.model.entity.Pokemon
import br.com.eduardotanaka.pokedex.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : BaseActivity() {
    var pokemon: Pokemon? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        pokemon = intent.getParcelableExtra<Pokemon>("POKEMON")

        Glide.with(this).load(this.pokemon?.urlImage)
            .listener(
                GlidePalette.with(this.pokemon?.urlImage)
                    .intoCallBack {
                        supportActionBar?.title = pokemon?.name?.toUpperCase()
                        supportActionBar?.setBackgroundDrawable(
                            ColorDrawable(
                                it!!.getMutedColor(
                                    BitmapPalette.Profile.MUTED
                                )
                            )
                        )
                        supportActionBar?.elevation = 0.0F
                        window.statusBarColor = it!!.getMutedColor(BitmapPalette.Profile.MUTED)
                        pokemon_card.setCardBackgroundColor(
                            it!!.getMutedColor(
                                BitmapPalette.Profile.MUTED
                            )
                        )
                    }
            )
            .into(pokemon_detail_image)

        pokemon_card.transitionName = "pokemon_${pokemon?.name}"
        pokemon_type.text = pokemon?.types?.split(",")?.get(0)
        pokemon_weight.text = "weight: ${pokemon?.weight?.div(10.0)} kg"
        pokemon_height.text = "height: ${pokemon?.height?.div(10.0)} m"
        pokemon_hp.text = pokemon?.hp.toString()
        pokemon_atk.text = pokemon?.attack.toString()
        pokemon_def.text = pokemon?.defense.toString()
        pokemon_satk.text = pokemon?.specialAttack.toString()
        pokemon_sdef.text = pokemon?.specialDefense.toString()
        pokemon_spd.text = pokemon?.speed.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_pokemon, menu);
        return true;
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(0)?.title = "#${this.pokemon?.id.toString().padStart(3, '0')}"
        return true;
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }
}