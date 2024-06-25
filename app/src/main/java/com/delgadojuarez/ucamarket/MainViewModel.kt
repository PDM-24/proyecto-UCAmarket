package com.delgadojuarez.ucamarket

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delgadojuarez.ucamarket.data.remote.model.ApiEtiqueta
import com.delgadojuarez.ucamarket.data.remote.model.ApiProduct
import com.delgadojuarez.ucamarket.data.remote.model.ApiServices
import com.delgadojuarez.ucamarket.data.remote.model.ApiUser
import com.delgadojuarez.ucamarket.domain.etiquetaUseCase
import com.delgadojuarez.ucamarket.domain.productUseCase
import com.delgadojuarez.ucamarket.domain.serviceUseCase
import com.delgadojuarez.ucamarket.domain.userUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {

    // Estados que gestionan el estado de la interfaz
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    // API Use Case
    private val userApi = userUseCase()
    private val productApi = productUseCase()
    private val serviceApi = serviceUseCase()
    private val etiquetaApi = etiquetaUseCase()

    // Token del usuario
    private val _myToken = mutableStateOf("")

    // datos en vista
    private val _userData = mutableStateOf(ApiUser())
    val userData: State<ApiUser> = _userData

    private val _product = mutableStateOf(ApiProduct())
    val productData: State<ApiProduct> = _product

    private val _serviceData = mutableStateOf(ApiServices())
    val serviceData: State<ApiServices> = _serviceData

    private val _etiquetaData = mutableStateOf(ApiEtiqueta())
    val etiquetaData: State<ApiEtiqueta> = _etiquetaData

    // Listas en vista
    private val _users = mutableStateOf<List<ApiUser>>(emptyList())
    val users: State<List<ApiUser>> = _users

    private val _products = mutableStateOf<List<ApiProduct>>(emptyList())
    val products: State<List<ApiProduct>> = _products

    private val _services = mutableStateOf<List<ApiServices>>(emptyList())
    val services: State<List<ApiServices>> = _services

    private val _etiquetas = mutableStateOf<List<ApiEtiqueta>>(emptyList())
    val etiquetas: State<List<ApiEtiqueta>> = _etiquetas

    // Funciones para UserUseCase
    fun registerUser(
        _nombre: String,
        _correo: String,
        _password: String,
        _isEmprendedor: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = userApi.register(
                    name = _nombre,
                    correo = _correo,
                    password = _password,
                    isEmprendedor = _isEmprendedor
                )
                Log.i("MainActivity", "User registered successfully")
                _uiState.value = UiState.Success("User registered successfully")
            } catch (e: HttpException) {
                Log.e("MainActivity", "Failed to register user: ${e.message()}")
                _uiState.value = UiState.Error("Failed to register user: ${e.message()}")
            } catch (e: Exception) {
                Log.e("MainActivity", "Failed to register user: ${e.message}")
                _uiState.value = UiState.Error("Failed to register user: ${e.message}")
            }
        }
    }

    fun loginUser(_correo: String, _password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = userApi.login(
                    correo = _correo,
                    password = _password
                )
                _myToken.value = response.token.toString()
                Log.i("MainActivity", "User logged in successfully")

                _uiState.value = UiState.Success("User logged in successfully")
            } catch (e: HttpException) {
                Log.e("MainActivity", "Failed to login user: ${e.message()}")
                _uiState.value = UiState.Error("Failed to login user: ${e.message()}")
            } catch (e: Exception) {
                Log.e("MainActivity", "Failed to login user: ${e.message}")
                _uiState.value = UiState.Error("Failed to login user: ${e.message}")
            }
        }
    }

    fun getAboutMe() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = userApi.getAboutMe(_myToken.value)
                _userData.value = response
                Log.i("MainActivity", "User details retrieved successfully")
                _uiState.value = UiState.Success("User details retrieved successfully")
            }catch (e: HttpException) {
                Log.e("MainActivity", "Failed to retrieve user details: ${e.message()}")
                _uiState.value = UiState.Error("Failed to retrieve user details: ${e.message()}")
            }catch (e: Exception) {
                Log.e("MainActivity", "Failed to retrieve user details: ${e.message}")
                _uiState.value = UiState.Error("Failed to retrieve user details: ${e.message}")
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val usersList = userApi.findAll()
                _users.value = usersList
                _uiState.value = UiState.Success("Users loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load users: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load users: ${e.message}")
            }
        }
    }

    fun getUserById(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val user = userApi.findOne(id)
                _userData.value = user
                _uiState.value = UiState.Success("User loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load user: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load user: ${e.message}")
            }
        }
    }

    fun updateUser(apiUser: ApiUser) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val updateResponse = userApi.updateUser(_myToken.value, apiUser)
                // Optionally update local state or perform additional actions
                _uiState.value = UiState.Success("User updated successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to update user: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to update user: ${e.message}")
            }
        }
    }

    fun changePassword(newPassword: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val changePasswordResponse = userApi.changePassword(_myToken.value, newPassword)
                // Optionally handle changePasswordResponse
                _uiState.value = UiState.Success("Password changed successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to change password: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to change password: ${e.message}")
            }
        }
    }

    fun editWishlist(itemId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val wishlistResponse = userApi.editWishlist(_myToken.value, itemId)
                // Optionally handle wishlistResponse
                _uiState.value = UiState.Success("Wishlist edited successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to edit wishlist: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to edit wishlist: ${e.message}")
            }
        }
    }

    // Funciones para Product Use Cases

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val productsList = productApi.findAll()
                _products.value = productsList
                _uiState.value = UiState.Success("Products loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message}")
            }
        }
    }

    fun getProductById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val product = productApi.findOne(id)
                _product.value = product
                _uiState.value = UiState.Success("Product loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load product: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load product: ${e.message}")
            }
        }
    }

    fun saveProduct(id: String?, apiProduct: ApiProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val updateResponse = productApi.save(_myToken.value, id, apiProduct)
                // Optionally update local state or perform additional actions
                _uiState.value = UiState.Success("Product updated successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to update product: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to update product: ${e.message}")
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val deleteResponse = productApi.delete(_myToken.value, id)
                // Optionally handle deleteResponse
                _uiState.value = UiState.Success("Product deleted successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to delete product: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to delete product: ${e.message}")
            }
        }
    }

    fun changeProductStatus(id: String, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val changeStatusResponse = productApi.changeStatus(_myToken.value, id, status)
                // Optionally handle changeStatusResponse
                _uiState.value = UiState.Success("Product status changed successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to change product status: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to change product status: ${e.message}")
            }
        }
    }

    fun getProductsByEtiqueta(etiquetaId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val productsList = productApi.findByEtiqueta(etiquetaId)
                _products.value = productsList
                _uiState.value = UiState.Success("Products loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message}")
            }
        }
    }

    fun getProductsByEmprendimiento(empId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val productsList = productApi.findByEmprendimiento(empId)
                _products.value = productsList
                _uiState.value = UiState.Success("Products loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message}")
            }
        }
    }

    fun getOwnProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val productsList = productApi.findOwn(_myToken.value)
                _products.value = productsList
                _uiState.value = UiState.Success("Products loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load products: ${e.message}")
            }
        }
    }

    // Funciones para Service Use Cases

    fun getServices() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val servicesList = serviceApi.findAll()
                _services.value = servicesList
                _uiState.value = UiState.Success("Services loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load services: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load services: ${e.message}")
            }
        }
    }

    fun getServiceById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val service = serviceApi.findOne(id)
                _serviceData.value = service
                _uiState.value = UiState.Success("Service loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load service: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load service: ${e.message}")
            }
        }
    }

    fun saveService(id: String?, apiService: ApiServices) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val updateResponse = serviceApi.save(_myToken.value, id, apiService)
                // Optionally update local state or perform additional actions
                _uiState.value = UiState.Success("Service updated successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to update service: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to update service: ${e.message}")
            }
        }
    }

    fun deleteService(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val deleteResponse = serviceApi.delete(_myToken.value, id)
                // Optionally handle deleteResponse
                _uiState.value = UiState.Success("Service deleted successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to delete service: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to delete service: ${e.message}")
            }
        }
    }

    fun getServicesByEmprendimiento(empId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val servicesList = serviceApi.findByEmprendimiento(empId)
                _services.value = servicesList
                _uiState.value = UiState.Success("Services loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load services: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load services: ${e.message}")
            }
        }
    }

    fun getOwnServices() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val servicesList = serviceApi.findOwn(_myToken.value)
                _services.value = servicesList
                _uiState.value = UiState.Success("Services loaded successfully")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Failed to load services: ${e.message()}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to load services: ${e.message}")
            }
        }
    }

    // Funciones para EtiquetaUseCase
    fun createEtiqueta(data: ApiEtiqueta) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = etiquetaApi.create(data)
                _uiState.value = UiState.Success("Etiqueta created successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to create etiqueta: ${e.message}")
            }
        }
    }

    fun findAllEtiquetas() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = etiquetaApi.findAll()
                _etiquetas.value = response
                _uiState.value = UiState.Success("Etiquetas retrieved successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to retrieve etiquetas: ${e.message}")
            }
        }
    }

    fun findOneEtiqueta(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = etiquetaApi.findOne(id)
                _etiquetaData.value = response
                _uiState.value = UiState.Success("Etiqueta retrieved successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to retrieve etiqueta: ${e.message}")
            }
        }
    }

    fun deleteEtiqueta(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val response = etiquetaApi.delete(id)
                _uiState.value = UiState.Success("Etiqueta deleted successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to delete etiqueta: ${e.message}")
            }
        }
    }

}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success (val msg : String) : UiState()
    data class Error (val msg : String) : UiState()
}