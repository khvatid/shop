package khvatid.shop.di


import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import khvatid.shop.data.repository.ContentRepositoryImp
import khvatid.shop.data.repository.UserRepositoryImp
import khvatid.shop.data.storage.RemoteStorage
import khvatid.shop.data.storage.ShortUserStorage
import khvatid.shop.data.storage.UserStorage
import khvatid.shop.data.storage.pref.ShortUserStoragePref
import khvatid.shop.data.storage.retrofit.ApiService
import khvatid.shop.data.storage.retrofit.NetworkService
import khvatid.shop.data.storage.retrofit.RemoteStorageImp
import khvatid.shop.data.storage.room.ShopDatabase
import khvatid.shop.data.storage.room.UserStorageImp
import khvatid.shop.domain.repository.ContentRepository
import khvatid.shop.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideShopDatabase(@ApplicationContext context: Context): ShopDatabase {
        return ShopDatabase.getInstance(context = context)
    }

    @Provides
    fun provideUserStorage(database: ShopDatabase): UserStorage {
        return UserStorageImp(database.userDao())
    }

    @Provides
    fun provideShortUserStorage(@ApplicationContext context: Context): ShortUserStorage {
        return ShortUserStoragePref(context = context)
    }

    @Provides
    fun provideUserRepository(
        userStorage: UserStorage,
        shortUserStorage: ShortUserStorage
    ): UserRepository {
        return UserRepositoryImp(userStorage, shortUserStorage)
    }

    @Provides
    fun provideApiService():ApiService{
        return NetworkService().execute()
    }

    @Provides
    fun provideRemoteStorage(service: ApiService):RemoteStorage{
        return RemoteStorageImp(service = service)
    }

    @Provides
    fun provideContentRepository(storage: RemoteStorage): ContentRepository{
        return ContentRepositoryImp(storage = storage)
    }
}