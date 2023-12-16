package com.juanma_gutierrez.snapshop.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.juanma_gutierrez.snapshop.R
import com.juanma_gutierrez.snapshop.data.api.ProductApi
import com.juanma_gutierrez.snapshop.data.models.User
import com.juanma_gutierrez.snapshop.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Fragmento que muestra detalles del usuario.
 */
class UserFragment : Fragment() {

    /**
     * Método invocado cuando la actividad asociada al fragmento ha sido creada.
     * Se utiliza para ocultar la barra superior (toolbar) de la actividad principal, si está presente.
     *
     * @param savedInstanceState El estado de la instancia guardada de la actividad.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as? MainActivity
        mainActivity?.hideTopToolBar()
    }

    /**
     * Método invocado cuando la actividad está creando su instancia.
     * Se utiliza para cargar la información del usuario con identificador "1".
     *
     * @param savedInstanceState El estado de la instancia guardada de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUser("1") // Carga el usuario 1
    }

    /**
     * Método llamado para crear y devolver la vista asociada al fragmento.
     * Infla el diseño del fragmento a partir del recurso de diseño definido en R.layout.fragment_user.
     *
     * @param inflater El inflador que se utiliza para inflar el diseño del fragmento.
     * @param container El contenedor en el que se debe colocar la vista del fragmento.
     * @param savedInstanceState El estado de la instancia guardada del fragmento.
     * @return La vista del fragmento o null si no se puede crear la vista.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    /**
     * Configura y devuelve una instancia de Retrofit para realizar llamadas a la API.
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Carga los detalles del usuario utilizando Retrofit y la API de productos falsos.
     * @param userId El ID del usuario a cargar.
     */
    private fun loadUser(userId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val user =
                    getRetrofit().create(ProductApi::class.java).getUserDetail(userId).asUser()
                loadData(user)
            } catch (e: Exception) {
                Log.e("error", "Error al cargar el usuario", e)
            }
        }
    }

    /**
     * Llena la interfaz con los datos del usuario.
     * @param user El objeto User con los detalles del usuario.
     */
    private fun loadData(user: User) {
        val userName: TextView? = view?.findViewById(R.id.tv_frUser_user_name)
        val name: TextView? = view?.findViewById(R.id.tv_frUser_first_name)
        val surname: TextView? = view?.findViewById(R.id.tv_fruser_surname)
        val email: TextView? = view?.findViewById(R.id.tv_fruser_email)
        val city: TextView? = view?.findViewById(R.id.tv_fruser_city)
        val street: TextView? = view?.findViewById(R.id.tv_fruser_street)
        val number: TextView? = view?.findViewById(R.id.tv_fruser_number)
        val zipCode: TextView? = view?.findViewById(R.id.tv_fruser_zipcode)
        val phone: TextView? = view?.findViewById(R.id.tv_fruser_phone)

        // Llena los elementos de la interfaz con los datos del usuario
        userName?.text = user.userName
        name?.text = user.firstName
        surname?.text = user.surname
        email?.text = user.email
        city?.text = user.city
        street?.text = user.street
        number?.text = user.number.toString()
        zipCode?.text = user.zipcode
        phone?.text = user.phone
    }
}