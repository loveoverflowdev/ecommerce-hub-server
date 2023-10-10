package database.schemas.membership

import database.schemas.base.BaseEntity
import database.schemas.base.BaseSchema
import database.schemas.base.BaseTable
import database.schemas.cart.CartTable
import database.schemas.product.ProductEntity
import database.schemas.product.ProductTable
import models.base.Model
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.sql.SQLIntegrityConstraintViolationException

data class CartProductMembershipEntity(
    override val id: String,
    val cartId: String,
    val courseId: String,
) : BaseEntity(id) {
    override fun toModel(): Model {
        TODO("Not yet implemented")
    }
}

object CartProductMembershipTable: BaseTable("cart_course_membership") {
    val courseId = reference("course_id", ProductTable.id)
    val cartId = reference("cart_id", CartTable.id)

    override val primaryKey = PrimaryKey(id)
}

class CartProductMembershipSchema(
    database: Database
) : BaseSchema<CartProductMembershipTable, CartProductMembershipEntity>(database) {

    suspend fun getProductListInCart(cartId: String) : List<ProductEntity> = dbQuery {
        ProductTable.innerJoin(
            CartProductMembershipTable,
            {ProductTable.id},
            {CartProductMembershipTable.courseId}
        ).select {
            CartProductMembershipTable.cartId.eq(cartId)
        }.map {
            ProductEntity(
                id = it[ProductTable.id].value,
                title = it[ProductTable.title],
                coverImage = it[ProductTable.coverImage],
                primaryCoins = it[ProductTable.primaryCoins],
                secondaryCoins = it[ProductTable.secondaryCoins],
                description = it[ProductTable.description],
            )
        }
    }

    suspend fun addProductToCart(cartId: String, courseId: String) = dbQuery {
        create(CartProductMembershipEntity(
            id = "",
            cartId = cartId,
            courseId = courseId,
        ))
    }

    suspend fun removeProductFromCart(cartId: String, courseId: String) = dbQuery {
        val deletedRowCount = CartProductMembershipTable.deleteWhere {
            CartProductMembershipTable.cartId.eq(cartId)
                .and(CartProductMembershipTable.courseId.eq(courseId))
        }
        if (deletedRowCount <= 0) {
            throw SQLIntegrityConstraintViolationException("this course did not exist in the cart")
        }
    }

    override suspend fun create(entity: CartProductMembershipEntity)
    : CartProductMembershipEntity = dbQuery {
        CartProductMembershipTable.insert {
            it[courseId] = entity.courseId
            it[cartId] = entity.cartId
        }.run {
            CartProductMembershipEntity(
                id = this[CartProductMembershipTable.id].value,
                courseId = this[CartProductMembershipTable.courseId].value,
                cartId = this[CartProductMembershipTable.cartId].value
            )
        }
    }

    override suspend fun read(id: String): CartProductMembershipEntity? = dbQuery {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): CartProductMembershipEntity? = dbQuery {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: String, entity: CartProductMembershipEntity)
    : CartProductMembershipEntity? = dbQuery {
        TODO("Not yet implemented")
    }
}
