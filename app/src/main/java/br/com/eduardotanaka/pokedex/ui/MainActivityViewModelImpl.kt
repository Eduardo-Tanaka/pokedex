package br.com.eduardotanaka.pokedex.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.eduardotanaka.pokedex.R
import br.com.eduardotanaka.pokedex.data.model.entity.base.PokemonSpecies
import br.com.eduardotanaka.pokedex.data.repository.PokedexRepository
import br.com.eduardotanaka.pokedex.ui.base.BaseViewModel
import br.com.eduardotanaka.pokedex.ui.base.StatefulResource
import javax.inject.Inject

class MainActivityViewModelImpl @Inject constructor(
    private val pokedexRepository: PokedexRepository,
) : BaseViewModel(), MainActivityViewModel {

    private val mutablePokemonSpecies: MutableLiveData<StatefulResource<PokemonSpecies>> = MutableLiveData()
    override val pokemonSpecies: LiveData<StatefulResource<PokemonSpecies>> = mutablePokemonSpecies

    override fun getPokemonByGeneration(generation: Int) {
        launch {
            mutablePokemonSpecies.value = StatefulResource.with(StatefulResource.State.LOADING)
            val resource = pokedexRepository.getGeneration(1)
            when {
                resource.hasData() -> {
                    //return the value
                    mutablePokemonSpecies.value = StatefulResource.success(resource)
                }
                resource.isNetworkIssue() -> {
                    mutablePokemonSpecies.value = StatefulResource<PokemonSpecies>()
                        .apply {
                            setMessage(R.string.no_network_connection)
                            setState(StatefulResource.State.ERROR_NETWORK)
                        }
                }
                resource.isApiIssue() -> //TODO 4xx isn't necessarily a service error, expand this to sniff http code before saying service error
                    mutablePokemonSpecies.value = StatefulResource<PokemonSpecies>()
                        .apply {
                            setState(StatefulResource.State.ERROR_API)
                            setMessage(R.string.service_error)
                        }
                else -> mutablePokemonSpecies.value = StatefulResource<PokemonSpecies>()
                    .apply {
                        setState(StatefulResource.State.SUCCESS)
                        setMessage(R.string.not_found)
                    }
            }
        }
    }
}