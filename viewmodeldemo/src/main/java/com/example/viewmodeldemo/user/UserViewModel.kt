package com.example.viewmodeldemo.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModel(private var user: User) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData = _userLiveData

    companion object {
        fun provideFactory(user: User): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                        return UserViewModel(user) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    fun showUser() {
        userLiveData.postValue(user)
    }

    fun changeUser() {
        user = User("小黑", 28, arrayOf<String>("上海市", "浦东区"))
        userLiveData.postValue(user)
    }
}

class UserViewModelFactory(private val user: User) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


