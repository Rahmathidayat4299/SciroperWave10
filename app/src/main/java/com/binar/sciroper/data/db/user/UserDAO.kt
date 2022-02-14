package com.binar.sciroper.data.db.user

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User): Int

    @Update
    suspend fun updateUser(user: User): Int

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE id_binar = :idBinar")
    fun getUserById(idBinar: String): LiveData<User>

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE id_binar = :idBinar")
    fun getUserId(idBinar: String): User

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE username = :username")
    suspend fun getUserByUserName(username: String): User

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE username = :username OR email = :email")
    fun getUserByUsernameAndEmail(username: String, email: String): User

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): User

    @Query("DELETE FROM ${User.TABLE_NAME}")
    suspend fun clearAllUser(): Int

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun getUsers(): LiveData<List<User>>

    @Query("UPDATE ${User.TABLE_NAME} SET avatar_id = :avatarId, username = :username, email= :email,password = :newPass WHERE id_binar = :idBinar")
    fun updateProfileById(
        avatarId: Int,
        username: String,
        email: String,
        newPass: String,
        idBinar: String
    ): Int

    @Query("select * from ${User.TABLE_NAME} except select * from ${User.TABLE_NAME} where id_binar  = :idBinar")
    fun getUserExcl(idBinar: String): List<User>

    @Query("select * from ${User.TABLE_NAME} except select * from ${User.TABLE_NAME} where id_binar  = :idBinar")
    fun getUserExclFlow(idBinar: String): Flow<List<User>>

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE id_binar = :idBinar")
    fun getUserByIdNoLiveData(idBinar: String): User
}