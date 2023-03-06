package khvatid.shop.data.storage

import khvatid.shop.data.storage.model.ShortUserStorageModel

interface ShortUserStorage {

    fun get(): ShortUserStorageModel
    fun set(shortUserStorageModel: ShortUserStorageModel): Throwable?

}