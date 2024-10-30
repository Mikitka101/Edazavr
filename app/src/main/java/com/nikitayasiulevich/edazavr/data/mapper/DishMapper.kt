package com.nikitayasiulevich.edazavr.data.mapper

import com.nikitayasiulevich.edazavr.data.model.RestaurantsListDto
import com.nikitayasiulevich.edazavr.domain.model.Restaurant

class DishMapper {

    /*fun mapDishesListDtoToDishes(restaurantsListDto: RestaurantsListDto): List<Restaurant> {
        val result = mutableListOf<Restaurant>()
        val restaurantsFromDto = restaurantsListDto.restaurants
        for (restaurantFromDto in restaurantsFromDto) {
            val restaurant = Restaurant(
                id = restaurantFromDto.id,
                name = restaurantFromDto.restaurantName,
                description = restaurantFromDto.restaurantDescription,
                address = restaurantFromDto.restaurantAddress,
                photoUrl = restaurantFromDto.restaurantPhotoUrl
            )
            result.add(restaurant)
        }
        return result
    }*/

    /*fun mapCollectionsListDtoToFeaturedCollections(collectionsListDto: CollectionsListDto): List<FeaturedCollection> {
        val result = mutableListOf<FeaturedCollection>()
        val collectionsFromDto = collectionsListDto.collections
        for (collectionFromDto in collectionsFromDto) {
            val featuredCollection = FeaturedCollection(
                id = collectionFromDto.id,
                title = collectionFromDto.title,
            )
            result.add(featuredCollection)
        }
        return result
    }*/

    /*fun mapDbModelToEntity(restaurantDbModel: RestaurantDbModel) = Restaurant(
        id = photoDbModel.id,
        height = photoDbModel.height,
        width = photoDbModel.width,
        url = photoDbModel.url,
        alt = photoDbModel.alt,
        avgColor = Color(photoDbModel.avgColor.toInt()),
        photographer = photoDbModel.photographer,
        srcOriginal = photoDbModel.srcOriginal,
        srcLarge = photoDbModel.srcLarge
    )

    fun mapEntityToDbModel(restaurant: Restaurant) = RestaurantDbModel(
        id = photo.id,
        height = photo.height,
        width = photo.width,
        url = photo.url,
        alt = photo.alt,
        avgColor = photo.avgColor.toArgb().toString(),
        photographer = photo.photographer,
        srcOriginal = photo.srcOriginal,
        srcLarge = photo.srcLarge
    )

    fun mapListDbModelToListEntity(list: List<PhotoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }*/
}