package ua.zt.mezon.graphomania.fsmandstrategydemo.datasources

import arrow.core.Either
import kotlinx.coroutines.flow.Flow

interface MainFragmentRepository {
    fun iniLoad()
    fun loadData(): Flow<Either<String, LoadItemData>>

}
