package com.asura.repositories.product

import database.DatabaseFactory
import database.schemas.product.ProductSchema
import models.products.base.Product

class ProductRepository: IProductRepository {

    private val courseSchema: ProductSchema = ProductSchema(
        database = DatabaseFactory.databaseShared
    )

    override suspend fun getAll(): List<Product> {
        return courseSchema.selectAll()
    }

    override suspend fun find(id: String): Product? {
        return courseSchema.findProduct(id)
    }

    override suspend fun find(predicate: (Product) -> Boolean): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun create(model: Product): Product {
        return courseSchema.createProduct(model)
    }

    override suspend fun delete(id: String): Product? {
        return courseSchema.deleteProduct(id)
    }

    override suspend fun update(id: String, model: Product): Product? {
        return courseSchema.updateProduct(id, model)
    }
}
