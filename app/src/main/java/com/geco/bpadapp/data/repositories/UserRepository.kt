package com.geco.bpadapp.data.repositories

import com.geco.bpadapp.data.api.APIService
import com.geco.bpadapp.data.db.UserDao
import com.geco.bpadapp.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserRepository(private val firebaseAuth: FirebaseAuth, private val userDao: UserDao, private val apiService: APIService) {

    fun login(email: String, password: String, onComplete: (FirebaseUser?, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(firebaseAuth.currentUser, null)
                } else {
                    onComplete(null, task.exception?.message)
                }
            }
    }

    suspend fun fetchUsersFromFirebase(authToken: String): List<User> {
        val response = apiService.getUsers(authToken)
        return if (response.isSuccessful) {
            response.body()?.values?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    // Insert a new user
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    // Get all users
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    // Get a user by their ID
    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)
    }

    // Get a user by email
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    // Update an existing user
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    // Delete a user by ID
    suspend fun deleteUserById(userId: String) {
        userDao.deleteUserById(userId)
    }
}
