package models.products.group

import models.products.base.Product

class ProductGroup(
    id: String,
    title: String,
    coverImage: String?,
    primaryCoins: Int,
    secondaryCoins: Int?,
    description: String,
): Product(
    id = id,
    title = title,
    coverImage = coverImage,
    primaryCoins = primaryCoins,
    secondaryCoins = secondaryCoins,
    description = description,
)
