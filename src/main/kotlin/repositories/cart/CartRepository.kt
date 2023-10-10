package com.asura.repositories.cart;

import database.DatabaseFactory
import database.schemas.cart.CartSchema
import database.schemas.membership.CartProductMembershipSchema
import models.products.base.Product
import repositories.cart.ICartRepository

class CartRepository: ICartRepository {

    private val cartSchema: CartSchema = CartSchema(
        database = DatabaseFactory.databaseShared
    )

    private val cartProductMembership: CartProductMembershipSchema = CartProductMembershipSchema(
        database = DatabaseFactory.databaseShared
    )


    override suspend fun getCartId(userId: String): String {
        return cartSchema.getCartId(userId)
    }

    override suspend fun getProductListInCart(cartId: String): List<Product> {
        return cartProductMembership
            .getProductListInCart(cartId)
            .map {
                Product(
                    id = it.id,
                    title = it.title,
                    coverImage = it.coverImage,
                    primaryCoins = it.primaryCoins,
                    secondaryCoins = it.secondaryCoins,
                    description = it.description,
                )
            }
    }

    override suspend fun addProductToCart(cartId: String, courseId: String) {
        cartProductMembership.addProductToCart(
            cartId = cartId,
            courseId = courseId,
        )
    }

    override suspend fun removeProductFromCart(cartId: String, courseId: String) {
        cartProductMembership.removeProductFromCart(
            cartId = cartId,
            courseId = courseId,
        )
    }

}
