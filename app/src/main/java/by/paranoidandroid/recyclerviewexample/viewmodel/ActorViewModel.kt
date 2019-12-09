package by.paranoidandroid.recyclerviewexample.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import by.paranoidandroid.recyclerviewexample.models.Actor
import by.paranoidandroid.recyclerviewexample.models.DataUtils

class ActorViewModel(
        savedStateHandle: SavedStateHandle
) : ViewModel() {

    /*private val actorName : String = savedStateHandle["name"] ?:
        throw IllegalArgumentException("missing name")*/
    private var _actor = MutableLiveData<Actor>()
    val actor : LiveData<Actor> get() = _actor

    @UiThread
    fun getMoreInfo(name: String) {
        val allActors = DataUtils.generateActors()
        // Just stub, no actual request
        Thread.sleep(1000L)
        _actor.value = allActors.find { it.name == name }
    }

}