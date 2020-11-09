package com.logistictest.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.logistictest.data.model.CAT_TABLE
import com.logistictest.data.model.CatEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCat(cat: CatEntity)

    @Query("SELECT * FROM $CAT_TABLE")
    abstract fun getCatsAsSingle(): Single<List<CatEntity>>

    @Query("SELECT * FROM $CAT_TABLE")
    abstract fun getCatsAsObservable(): Observable<List<CatEntity>>

    @Query("DELETE from $CAT_TABLE WHERE id=:id")
    abstract fun deleteCat(id: String)
}