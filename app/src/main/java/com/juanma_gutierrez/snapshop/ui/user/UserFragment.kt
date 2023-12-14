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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUser("1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    // Prepara la llamada de retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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

    private fun loadData(user: User) {
        val userName:TextView? = view?.findViewById(R.id.tv_frUser_user_name)
        val name: TextView? = view?.findViewById(R.id.tv_frUser_first_name)
        val surname: TextView? = view?.findViewById(R.id.tv_fruser_surname)
        val email: TextView? = view?.findViewById(R.id.tv_fruser_email)
        val city: TextView? = view?.findViewById(R.id.tv_fruser_city)
        val street: TextView? = view?.findViewById(R.id.tv_fruser_street)
        val number: TextView? = view?.findViewById(R.id.tv_fruser_number)
        val zipCode: TextView? = view?.findViewById(R.id.tv_fruser_zipcode)
        val phone: TextView? = view?.findViewById(R.id.tv_fruser_phone)
        
        userName?.text = user.userName
        name?.text = user.firstName
        surname?.text = user.surname
        email?.text = user.email
        city?.text = user.city
        street?.text = user.street
        number?.text = user.number.toString()
        zipCode?.text = user.zipcode
        phone?.text=user.phone
    }

}