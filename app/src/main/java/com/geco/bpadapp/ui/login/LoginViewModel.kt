package com.geco.bpadapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geco.bpadapp.data.models.User
import com.geco.bpadapp.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _authToken = MutableLiveData<String?>()
    val authToken: LiveData<String?> get() = _authToken

    private val _userFetchStatus = MutableLiveData<Boolean>()
    val userFetchStatus: LiveData<Boolean> get() = _userFetchStatus

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> get() = _loginStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        repository.login(email, password) { firebaseUser, error ->
            if (firebaseUser != null) {
                viewModelScope.launch {
                    val user = User(
                        id = firebaseUser.uid,
                        name = firebaseUser.displayName ?: "Unknown",
                        email = firebaseUser.email ?: email,
                        phoneNumber = firebaseUser.phoneNumber ?: ""
                    )
                    repository.insertUser(user)
                    fetchAuthToken(firebaseUser)
                }
                onResult(true, null)
            } else {
                onResult(false, error)
            }
        }
    }

    private suspend fun fetchAuthToken(user: FirebaseUser?) {
        try {
            val tokenResult = user?.getIdToken(true)?.await()
            _authToken.value = tokenResult?.token
            _loginStatus.value = true
        } catch (e: Exception) {
            _errorMessage.value = "Gagal mendapatkan token: ${e.message}"
        }
    }

    fun fetchUsers(authToken: String) {
        viewModelScope.launch {
            val usersFromFirebase = repository.fetchUsersFromFirebase(authToken)
            usersFromFirebase.forEach { user ->
                repository.insertUser(user)
            }
            _userFetchStatus.value = true
        }
    }
}