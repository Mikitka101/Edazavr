package com.nikitayasiulevich.edazavr.data.mapper

import com.nikitayasiulevich.edazavr.data.model.UserDTO
import com.nikitayasiulevich.edazavr.domain.model.User

class UserMapper {

    /*fun mapRestaurantsListDtoToRestaurants(restaurantsListDto: RestaurantsListDto): List<Restaurant> {
        val result = mutableListOf<Restaurant>()
        val restaurantsFromDto = restaurantsListDto.restaurants
        for (restaurantFromDto in restaurantsFromDto) {
            val restaurant = Restaurant(
                id = restaurantFromDto.id.toString(),
                admin = restaurantFromDto.admin.toString(),
                name = restaurantFromDto.name,
                description = restaurantFromDto.description,
                address = restaurantFromDto.address,
                photoUrl = restaurantFromDto.photo,
                category = restaurantFromDto.category,
                isbBanned = restaurantFromDto.banned,
                isActive = restaurantFromDto.active
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

    fun mapDtoToEntity(userDTO: UserDTO) = User(
        id = userDTO.id.toString(),
        login = userDTO.login,
        name = userDTO.name,
        roles = userDTO.roles
    )
    /*fun mapEntityToDbModel(restaurant: Restaurant) = RestaurantDbModel(
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