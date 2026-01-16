package com.example.tugasakhir.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("""
        SELECT * FROM user
        WHERE username = :username 
        AND password = :password
    """)
    suspend fun login(username: String, password: String): UserEntity?

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT COUNT(*) FROM user")
    suspend fun countUser(): Int

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

}
