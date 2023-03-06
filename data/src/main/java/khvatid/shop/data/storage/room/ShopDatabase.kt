package khvatid.shop.data.storage.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import khvatid.shop.data.storage.room.dao.MovieDao
import khvatid.shop.data.storage.room.dao.SeasonDao
import khvatid.shop.data.storage.room.dao.UserDao
import khvatid.shop.data.storage.room.entity.MovieEntity
import khvatid.shop.data.storage.room.entity.SeasonEntity
import khvatid.shop.data.storage.room.entity.UserEntity


@Database(
    entities = [(UserEntity::class)],
    exportSchema = true,
    version = 1,
)
abstract class ShopDatabase() : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: ShopDatabase? = null

        fun getInstance(context: Context): ShopDatabase {
            synchronized(this) {
                var instance: ShopDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopDatabase::class.java,
                        "khvatid_shop_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
