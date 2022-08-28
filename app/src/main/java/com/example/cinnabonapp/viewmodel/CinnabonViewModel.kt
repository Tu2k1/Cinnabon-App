package com.example.cinnabonapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


private const val CINNABON_PRICE = 15.00

private const val DELIVERY_PRICE = 5.00
class CinnabonViewModel : ViewModel() {

    private var _cinnabonType = MutableLiveData("")
    val cinnabonType: LiveData<String> = _cinnabonType

    private var _quantity = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity

    val dateOptions = getPickupOptions()

    private var _totalPrice = MutableLiveData(0.0)
    val totalPrice: LiveData<String> = Transformations.map(_totalPrice){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private var _date = MutableLiveData("")
    val date: LiveData<String> = _date

    init {
        resetOrder()
    }
    fun setCinnabonType(type:String){
        _cinnabonType.value = type
        updatePrice()
    }

    fun setQuantity(quantity:Int){
        _quantity.value = quantity
        updatePrice()
    }

    fun setDate(date:String){
        _date.value = date
        updatePrice()
    }
    fun resetOrder() {
        _quantity.value = 1
        _cinnabonType.value = ""
        _date.value = dateOptions[0]
        _totalPrice.value = 0.0
    }

    private fun getPickupOptions(): List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calender = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calender.time))
            calender.add(Calendar.DATE,1)
        }
        return options
    }
    private fun updatePrice(){
        val calculatedPrice = (quantity.value ?: 0) * CINNABON_PRICE + DELIVERY_PRICE

        _totalPrice.value = calculatedPrice
    }
}