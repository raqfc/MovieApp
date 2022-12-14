package br.com.raqfc.movieapp.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.raqfc.movieapp.data.local.entity.BaseRoomEntity

abstract class  BaseDAO<T: BaseRoomEntity<*>>(private val tableName: String)  {
    @Insert
    abstract suspend fun insert(entity: T)

    @Insert
    abstract suspend fun insert(entities: List<T>)

    @Update
    abstract suspend fun update(entity: T)

    @Update
    abstract suspend fun update(entities: List<T>)

    @Delete
    abstract suspend fun delete(entity: T)

    @Delete
    abstract suspend fun delete(entities: List<T>)

    @RawQuery
    protected abstract suspend  fun deleteAll(query: SupportSQLiteQuery): Int

    suspend fun deleteAll(): Int {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName")
        return deleteAll(query)
    }

    @RawQuery
    protected abstract suspend fun getAll(query: SupportSQLiteQuery): List<T>

    suspend fun getAll(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
        return getAll(query)
    }
//    @RawQuery(observedEntities = listOf(clazz))
//    protected abstract fun observeAll(query: SupportSQLiteQuery): Flow<List<T>>
//
//    fun observeAll(): Flow<List<T>> {
//        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
//        return observeAll(query)
//    }
}