package com.example.moneda

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCantidad: EditText
    private lateinit var radioDolares: RadioButton
    private lateinit var radioSoles: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a vistas (IDs corregidos)
        editTextCantidad = findViewById(R.id.editTextText)
        radioDolares = findViewById(R.id.radioDolares)
        radioSoles = findViewById(R.id.radioSoles)
    }

    fun miClicManejador(view: View) {
        if (view.id == R.id.btnConvertir) {
            convertirMoneda()
        }
    }

    private fun convertirMoneda() {

        val cantidadTexto = editTextCantidad.text.toString().trim()

        // Validación 1: campo vacío
        if (cantidadTexto.isEmpty()) {
            mostrarError("Ingresa una cantidad")
            return
        }

        // Validación 2: número válido
        val cantidad = cantidadTexto.toFloatOrNull()
        if (cantidad == null || cantidad <= 0) {
            mostrarError("Ingresa un número válido mayor a 0")
            return
        }

        val tipoCambio = 3.65f

        if (radioDolares.isChecked) {
            val resultado = cantidad * tipoCambio
            mostrarResultado(
                "${"%.2f".format(cantidad)} dólares = ${"%.2f".format(resultado)} soles"
            )
        } else if (radioSoles.isChecked) {
            val resultado = cantidad / tipoCambio
            mostrarResultado(
                "${"%.2f".format(cantidad)} soles = ${"%.2f".format(resultado)} dólares"
            )
        } else {
            mostrarError("Selecciona una opción")
        }
    }

    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        editTextCantidad.requestFocus()
    }

    private fun mostrarResultado(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}