package com.geco.bpadapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.geco.bpadapp.data.models.User

@Dao
interface UserDao {

    // Insert a new user into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    // Insert multiple users
    @Insert
    suspend fun insertUsers(users: List<User>)

    // Get a user by their ID
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): User?

    // Get all users
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    // Get user by email
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    // Update an existing user's details
    @Update
    suspend fun updateUser(user: User)

    // Delete a user by ID
    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)
}