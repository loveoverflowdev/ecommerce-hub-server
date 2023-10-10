package commands

import com.google.gson.annotations.SerializedName

class CustomerLogInCommand(
    @SerializedName(value = "usernameOrEmail")
    val usernameOrEmail: String?,
    @SerializedName(value = "password")
    val password: String?,
)

class CustomerRegisterCommand(
    @SerializedName(value = "username")
    val username: String?,
    @SerializedName(value="email")
    val email: String?,
    @SerializedName(value="password")
    val password: String?,
    @SerializedName(value="phoneNumber")
    val phoneNumber: String?
)

class GetProductListInCustomerCartCommand(
    val customerId: String,
)

data class AddProductToCustomerCartCommand(
    @SerializedName(value="courseId")
    val courseId: String?,
    val customerId: String
)

class RemoveProductFromCartCommand(
    @SerializedName(value="courseId")
    val courseId: String?,
    val customerId: String,
)
