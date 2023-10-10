package commands

import com.google.gson.annotations.SerializedName
import models.products.base.Product

class SellerLogInCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value = "password")
    val password: String?,
)


class CreateProductCommand(
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "instructor")
    val instructor: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?,
    val tags: List<String>?,
)

class UpdateProductCommand(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "instructor")
    val instructor: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?,
    val tags: MutableList<String>? = mutableListOf()
)

class DeleteProductCommand(val id: String)

class UpdateProductGroupCommand(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?,
)

class CreateProductGroupCommand(
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "title")
    val title: String?,
    @SerializedName(value = "coverImage")
    val coverImage: String?,
    @SerializedName(value = "description")
    val description: String?,
    @SerializedName(value = "primaryCoins")
    val primaryCoins: Int?,
    @SerializedName(value = "secondaryCoins")
    val secondaryCoins: Int?
)

class DeleteProductGroupCommand(
    val id: String?
)

class AddProductToGroupCommand(
    @SerializedName(value = "courseId")
    val courseId: String?,
    @SerializedName(value = "courseGroupId")
    val courseGroupId: String?
)

fun CreateProductCommand.toModel(): Product {
    // val uniqueID = UUID.randomUUID().toString()
    return Product(
        id = "",
        title = title ?: "",
        coverImage = coverImage,
        description = description ?: "",
        primaryCoins = primaryCoins ?: 0,
        secondaryCoins = secondaryCoins ?: 0
    );
}

fun UpdateProductCommand.toModel(): Product {
    return Product(
        id = id,
        title = title ?: "",
        coverImage = coverImage ?: "",
        description = description ?: "",
        primaryCoins = 0,
        secondaryCoins = 0
    );
}
