package repositories.products.group

import models.products.group.ProductGroup

interface IProductGroupRepository {
    suspend fun addProductToGroup(courseId: String, courseGroupId: String)
    suspend fun removeProductFromGroup(courseId: String, courseGroupId: String)
    suspend fun getAll(): List<ProductGroup>
    suspend fun find(id: String): ProductGroup?
    suspend fun create(model: ProductGroup): ProductGroup?
    suspend fun delete(id: String): ProductGroup?
    suspend fun update(id: String, model: ProductGroup): ProductGroup?
}
