package ua.zt.mezon.graphomania.fsmandstrategydemo.datasources

import android.content.Context
import arrow.core.Either
import com.don11995.log.SimpleLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main.MainFragmentUiStatesModel
import kotlin.random.Random
import kotlin.random.nextLong

class MockDataRepository(context: Context) : MainFragmentRepository {
    override fun iniLoad() {
        percent = 0
        errorState = ""
    }

    override fun loadData(): Flow<Either<String, LoadItemData>> = flow {
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(10)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(30)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(42)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(50)))
        delay()

        if (Random.nextBoolean()) {
            emit(Either.Left("The hurricane cut the connection"))
            return@flow
        }

        emit(Either.Right(LoadItemData.LoadCounterPercentData(70)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(90)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(94)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(99)))
        delay()
        emit(Either.Right(LoadItemData.LoadCounterPercentData(100)))
        if (Random.nextBoolean()) {
            emit(Either.Right(LoadItemData.ListShow(arrayListOf<ItemData>())))
            return@flow
        }
        emit(Either.Right(LoadItemData.ListShow(arrayListOf(ItemData("One"), ItemData("Two")))))
    }

    companion object {
        var loading = false
        var percent = 0
        var errorState = ""
    }

    private suspend fun delay() {
        kotlinx.coroutines.delay(Random.nextLong(500L..1200L))
    }
}