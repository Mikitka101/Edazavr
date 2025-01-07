package com.nikitayasiulevich.edazavr.data.mapper

import com.nikitayasiulevich.edazavr.data.model.DishesListDto
import com.nikitayasiulevich.edazavr.data.model.RestaurantsListDto
import com.nikitayasiulevich.edazavr.domain.model.Dish
import com.nikitayasiulevich.edazavr.domain.model.Restaurant

class DishMapper {

    fun mapDishesListDtoToDishes(dishesListDto: DishesListDto): List<Dish> {
        val result = mutableListOf<Dish>()
        val dishesFromDto = dishesListDto.dishes
        for (dishFromDto in dishesFromDto) {
            val dish = Dish(
                id = dishFromDto.id,
                name = dishFromDto.name,
                description = dishFromDto.description,
                photoUrl = dishFromDto.photo,
                price = dishFromDto.price
            )
            result.add(dish)
        }
        return result
    }

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