package com.asura.repositories.products.product

import models.products.product.Product

interface IProductRepository {
     suspend fun getAll(): List<Product>
     suspend fun find(id: String): Product?
     suspend fun find(predicate: (Product) -> Boolean): List<Product>
     suspend fun create(model: Product): Product
     suspend fun delete(id: String): Product?
     suspend fun update(id: String, model: Product): Product?
}
