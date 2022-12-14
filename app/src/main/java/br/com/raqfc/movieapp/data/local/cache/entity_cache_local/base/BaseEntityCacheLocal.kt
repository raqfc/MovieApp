package br.com.raqfc.movieapp.data.local.cache.entity_cache_local.base

import android.util.Log
import br.com.raqfc.movieapp.data.local.dao.BaseDAO
import br.com.raqfc.movieapp.data.local.entity.BaseRoomEntity
import br.com.raqfc.movieapp.data.local.entity.BaseRoomEntity.Companion.toModel
import br.com.raqfc.movieapp.domain.entities.BaseEntity
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


/**
 * @params E: Entidade Room, D: RoomDAO, M: Model do sistema
 */
abstract class BaseEntityCacheLocal<E : BaseRoomEntity<B>, D : BaseDAO<E>, B> where B : CacheableModel<E, B>, B: BaseEntity {
    private val mutex = Mutex()
    protected abstract val mDAO: D

    protected var memoryData: MutableList<B> = mutableListOf()


    /*    GET   */

    fun getInMemory(filter: (B) -> Boolean = { true }): List<B> {
        return memoryData.filter(filter)
    }

    suspend fun get(filter: (B) -> Boolean = { true }): List<B> {
        mutex.withLock {
            if (memoryData.isEmpty())
                memoryData = get()
            return memoryData.filter(filter)
        }
    }
    private suspend fun get(): MutableList<B> {
        return mDAO.getAll().toModel().toMutableList()
    }
    suspend fun getById(id: String): B? {
        mutex.withLock {
            if (memoryData.isEmpty())
                memoryData = get()
            return memoryData.firstOrNull { it.id == id }
        }
    }


    /*    INSERT/UPDATE   */

    suspend fun insertAll(entities: List<B>) {
        try {
            if (refreshedMemoryEntities().isEmpty()) {//init
                mDAO.insert(entities.map { it.toRoomEntity() })
                mutex.withLock { memoryData.addAll(entities.map { it.clone() }) }
            }
            for (entity in entities) {
                addOrUpdate(entity)
            }
        } catch (e: Exception) {
            logE(e,"insertAll", "Error inserting entities")
        }
    }

    suspend fun addOrUpdate(entity: B) {
        try {
            val index = memoryData.indexOfFirst { it.id == entity.id }
            if (index > -1) {
                doUpdate(entity, index)
            } else {
                doAdd(entity)
            }
        } catch (e: Exception) {
            logE(e,"addOrUpdate", "Error")
        }
    }

    private suspend fun doAdd(entity: B) {
        mDAO.insert(entity.toRoomEntity())
        mutex.withLock { memoryData.add(entity.clone()) }
    }

    private suspend fun doUpdate(entity: B, index: Int) {
        mDAO.update(entity.toRoomEntity())
        mutex.withLock { memoryData[index] = entity.clone() }
    }


    /*    DELETE   */

    suspend fun delete(entity: B) {
        try {
            mDAO.delete(entity.toRoomEntity())
            mutex.withLock { memoryData.removeIf { it.id == entity.id } }
        } catch (e: Exception) {
            logE(e,"delete", "Error deleting entity")
        }
    }

    suspend fun deleteAll() {
        try {
            mDAO.deleteAll()
            mutex.withLock { memoryData = mutableListOf() }
        } catch (e: Exception) {
            logE(e,"deleteAll", "Error deleting entities")
        }
    }

    private suspend fun refreshedMemoryEntities(): MutableList<B> {
        mutex.withLock {
            memoryData = get()
            return memoryData
        }
    }

    private fun logE(e: Exception, method: String, message: String) {
        Log.e("${javaClass.simpleName}.$method", message)
        e.printStackTrace()
    }
}