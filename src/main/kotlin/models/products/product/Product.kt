package models.products.product

import models.products.base.Product

class Product(
    id: String,
    title: String,
    coverImage: String?,
    primaryCoins: Int,
    secondaryCoins: Int?,
    description: String,

    val instructor: String
): Product(
    id = id,
    title = title,
    coverImage = coverImage,
    primaryCoins = primaryCoins,
    secondaryCoins = secondaryCoins,
    description = description,
)
