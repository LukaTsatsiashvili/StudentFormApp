package com.example.studentformapp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormViewModel : ViewModel() {

    var nameState by mutableStateOf("")
    var emailState by mutableStateOf("")
    var dateState by mutableStateOf("")
    var selectedOption by mutableStateOf("")
    var isAgreed by mutableStateOf(false)


    fun isFormValid(): Boolean {
        val fieldsFilled = nameState.isNotBlank() && emailState.isNotBlank() && dateState.isNotBlank()
        val optionSelected = selectedOption.isNotBlank()
        return fieldsFilled && optionSelected && isAgreed
    }
}