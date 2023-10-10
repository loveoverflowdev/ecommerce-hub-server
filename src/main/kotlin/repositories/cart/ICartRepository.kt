package repositories.cart

import models.products.base.Product

interface ICartRepository {
    suspend fun getCartId(userId: String): String
    suspend fun getProductListInCart(cartId: String): List<Product>
    suspend fun addProductToCart(cartId: String, courseId: String)
    suspend fun removeProductFromCart(cartId: String, courseId: String)
}
