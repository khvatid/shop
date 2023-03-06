package khvatid.shop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import khvatid.shop.domain.repository.ContentRepository
import khvatid.shop.domain.repository.UserRepository
import khvatid.shop.domain.usecase.database.GetShortUserUseCase
import khvatid.shop.domain.usecase.database.GetUserFromDatabaseUseCase
import khvatid.shop.domain.usecase.database.LoginUseCase
import khvatid.shop.domain.usecase.database.SignupUserUseCase
import khvatid.shop.domain.usecase.network.GetFlashSaleUseCase
import khvatid.shop.domain.usecase.network.GetLatestUseCase


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {


    @Provides
    fun provideGetUserFromDatabaseUseCase(repository: UserRepository): GetUserFromDatabaseUseCase {
        return GetUserFromDatabaseUseCase(repository = repository)
    }

    @Provides
    fun provideLoginUseCase(repository: UserRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideSignupUserUseCase(repository: UserRepository): SignupUserUseCase {
        return SignupUserUseCase(repository)
    }


    @Provides
    fun provideGetShortUserUseCase(repository: UserRepository): GetShortUserUseCase {
        return GetShortUserUseCase(repository)
    }

    @Provides
    fun provideGetFlashSaleUseCase(repository: ContentRepository): GetFlashSaleUseCase {
        return GetFlashSaleUseCase(repository)
    }

    @Provides
    fun provideGetLatestUseCase(repository: ContentRepository): GetLatestUseCase {
        return GetLatestUseCase(repository)
    }

}