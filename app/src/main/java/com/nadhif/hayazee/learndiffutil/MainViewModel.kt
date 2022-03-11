package com.nadhif.hayazee.learndiffutil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _userList = MutableLiveData<MutableList<User>>()
    val userList: LiveData<MutableList<User>> get() = _userList

    private var isAsc = false

    init {
        _userList.value = mutableListOf(
            User(2, "Bob", "081864672393"),
            User(1, "Fulan", "08564672393"),
            User(4, "Aslan", "08574222393"),
            User(3, "Jacky", "08223812122")
        )
    }

    fun setSelectedUser(user: User) {
        _userList.value?.onEach {
            it.isSelected = it.id == user.id
        }
        _userList.value = _userList.value
    }

    fun deleteUser(user: User) {
        _userList.value?.remove(user)
        _userList.value = _userList.value
    }

    fun sort() {
        if (isAsc) {
//            sort as desc
            val list = userList.value?.sortedByDescending { it.name }
            _userList.value = list?.toMutableList()
        } else {
//            sort as asc
            val list = userList.value?.sortedBy { it.name }
            _userList.value = list?.toMutableList()
        }
        isAsc = !isAsc
    }
}