package repositories.cart

import models.products.product.Product
import models.products.group.ProductGroup

interface ICartRepository {
    suspend fun getCartId(userId: String): String
    suspend fun getProductListInCart(cartId: String): List<Product>
    suspend fun addProductToCart(cartId: String, courseId: String)
    suspend fun removeProductFromCart(cartId: String, courseId: String)
    suspend fun getProductGroupListInCart(cartId: String): List<ProductGroup>
    suspend fun addProductGroupToCart(cartId: String, courseGroupId: String)
    suspend fun removeProductGroupFromCart(cartId: String, courseGroupId: String)
}
