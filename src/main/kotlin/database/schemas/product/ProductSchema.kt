package database.schemas.product

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import models.products.base.Product
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

data class ProductEntity(
    override val id: String,
    val title: String,
    val coverImage: String?,
    val primaryCoins: Int,
    val secondaryCoins: Int?,
    val description: String,
) : BaseEntity(id) {
    companion object {
        fun of(model: Product): ProductEntity {
            return ProductEntity(
                id = model.id,
                title = model.title,
                coverImage = model.coverImage,
                primaryCoins = model.primaryCoins,
                secondaryCoins = model.secondaryCoins,
                description = model.description
            )
        }
    }

    override fun toModel(): Product {
        return Product(
            id = this.id,
            title = this.title,
            coverImage = this.coverImage,
            primaryCoins = this.primaryCoins,
            secondaryCoins = this.secondaryCoins,
            description = this.description
        )
    }
}

object ProductTable: BaseTable("course") {
    val title = varchar("title", length = 255)
    val coverImage = varchar("cover_image", length = 255).nullable()
    val primaryCoins = integer("primary_coins")
    val secondaryCoins = integer("secondary_coins").nullable()
    val description = varchar("description", length = 255)
    val instructor = varchar("instructor", length = 255)

    override val primaryKey = PrimaryKey(id)
}

class ProductSchema(
    database: Database
) : BaseSchema<ProductTable, ProductEntity>(database) {
    suspend fun selectAll(): List<Product> = dbQuery {
        ProductTable
            .selectAll()
            .map {
                Product(
                    id = it[ProductTable.id].value,
                    title = it[ProductTable.title],
                    coverImage = it[ProductTable.coverImage],
                    primaryCoins = it[ProductTable.primaryCoins],
                    secondaryCoins = it[ProductTable.secondaryCoins],
                    description = it[ProductTable.description],
                )
            }
    }

    suspend fun findProduct(id: String): Product? {
        return read(id)?.toModel()
    }
    suspend fun createProduct(model: Product): Product {
        return create(ProductEntity.of(model)).toModel()
    }

    suspend fun deleteProduct(id: String): Product? {
        return delete(id)?.toModel()
    }

    suspend fun updateProduct(id: String, model: Product): Product? {
        return update(id, ProductEntity.of(model))?.toModel()
    }


    override suspend fun create(entity: ProductEntity)
    : ProductEntity = dbQuery {
         ProductTable.insert {
            it[title] = entity.title
            it[coverImage] = entity.coverImage
            it[primaryCoins] = entity.primaryCoins
            it[secondaryCoins] = entity.secondaryCoins
            it[description] = entity.description
        }.run {
             ProductEntity(
                 id = this[ProductTable.id].value,
                 title = this[ProductTable.title],
                 coverImage = this[ProductTable.coverImage],
                 primaryCoins = this[ProductTable.primaryCoins],
                 secondaryCoins = this[ProductTable.secondaryCoins],
                 description = this[ProductTable.description],
             )
        }
    }

    override suspend fun read(id: String)
    : ProductEntity? = dbQuery {
        ProductTable.select {
            ProductTable.id.eq(id)
        }.map {
            ProductEntity(
                id = it[ProductTable.id].value,
                title = it[ProductTable.title],
                coverImage = it[ProductTable.coverImage],
                primaryCoins = it[ProductTable.primaryCoins],
                secondaryCoins = it[ProductTable.secondaryCoins],
                description = it[ProductTable.description],
            )
        }.singleOrNull()
    }

    override suspend fun delete(id: String)
    : ProductEntity? = dbQuery {
        read(id)?.let { entity ->
            ProductTable.deleteWhere { ProductTable.id.eq(id) }.let { deleteResult ->
                if (deleteResult > 0) {
                    entity
                } else {
                    null
                }
            }
        }
    }

    override suspend fun update(id: String, entity: ProductEntity)
    : ProductEntity? = dbQuery {
        ProductTable.update({
            ProductTable.id.eq(id)
        }) {
            it[title] = entity.title
            it[coverImage] = entity.coverImage
            it[primaryCoins] = entity.primaryCoins
            it[secondaryCoins] = entity.secondaryCoins
            it[description] = entity.description
        }.let { updateResult ->
            if (updateResult > 0) {
                entity.copy(id = id)
            } else {
                null
            }
        }
    }
}
