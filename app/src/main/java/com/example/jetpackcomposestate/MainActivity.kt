package com.example.jetpackcomposestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposestate.ui.theme.JetpackComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloScreen()
        }
    }
}



class HelloViewModel: ViewModel(){


    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> = _name

    fun onNameChange(newName: String){
          _name.value = newName
    }

}


 class Car{

}


class Ford : Car(){

}

@Composable
fun HelloScreen(helloViewModel: HelloViewModel = viewModel()){
    val name by helloViewModel.name.observeAsState("")

    HelloContent(name = name, onNameChange = { helloViewModel.onNameChange(it)})

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent(name: String, onNameChange: (String)-> Unit){

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style =  MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = {Text("name")}
        )



    }
}

