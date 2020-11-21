/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  lateinit var appDatabase: AppDatabase

  private val listSportType = listOf<String>(
    "https://imgur.com/KliL0Ga.png",
    "https://imgur.com/U05qqhr.png",
    "https://imgur.com/3oel0y9.png",
    "https://imgur.com/cfAwGI8.png",
    "https://imgur.com/ySBdrr4.png"
    )

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    typeResponseConverter: TypeResponseConverter
  ): AppDatabase {
    appDatabase =  Room
      .databaseBuilder(application, AppDatabase::class.java, "Sporttrack.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
          super.onCreate(db)
          db.execSQL("INSERT INTO SportType (sportTypeId, name, caloriesBurnedPerMinute, imgPath) VALUES (0, 'Running', 11, '${listSportType[0]}')")
          db.execSQL("INSERT INTO SportType (sportTypeId, name, caloriesBurnedPerMinute, imgPath) VALUES (1, 'Basketball', 9, '${listSportType[1]}')")
          db.execSQL("INSERT INTO SportType (sportTypeId, name, caloriesBurnedPerMinute, imgPath) VALUES (2, 'Cycling', 9, '${listSportType[2]}')")
          db.execSQL("INSERT INTO SportType (sportTypeId, name, caloriesBurnedPerMinute, imgPath) VALUES (3, 'Football', 7, '${listSportType[3]}')")
          db.execSQL("INSERT INTO SportType (sportTypeId, name, caloriesBurnedPerMinute, imgPath) VALUES (4, 'Gymnastic', 5, '${listSportType[4]}')")
        }
      })
      .allowMainThreadQueries()
      .build()
    return appDatabase
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
    return TypeResponseConverter(moshi)
  }

  @Provides
  @Singleton
  fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
    return appDatabase.exerciseDao()
  }

  @Provides
  @Singleton
  fun provideSportTypeDao(appDatabase: AppDatabase): SportTypeDao {
    return appDatabase.sportTypeDao()
  }
}
