package com.example.creditohipotecario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enlazar los elementos del XML con el código
        val edtValorPropiedad = findViewById<EditText>(R.id.entrada1) // Valor de la propiedad (opcional)
        val edtMonto = findViewById<EditText>(R.id.entrada2) // VP (Monto del préstamo)
        val edtPlazo = findViewById<EditText>(R.id.entrada3) // n (Tiempo en meses)
        val edtTasa = findViewById<EditText>(R.id.entrada4) // TEA (Tasa de interés anual)
        val btnSimular = findViewById<Button>(R.id.boton1)
        val txtResultado = findViewById<TextView>(R.id.txtResultado) // Resultado de la cuota

        // Configurar el botón "Simular"
        btnSimular.setOnClickListener {
            val valorPropiedadStr = edtValorPropiedad.text.toString().trim()
            val montoStr = edtMonto.text.toString().trim()
            val plazoStr = edtPlazo.text.toString().trim()
            val tasaStr = edtTasa.text.toString().trim()

            if (montoStr.isEmpty() || plazoStr.isEmpty() || tasaStr.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                // Convertir valores a numéricos
                val VP = montoStr.toDouble() // Monto del préstamo
                val n = plazoStr.toInt() // Plazo en meses
                val TEA = tasaStr.toDouble() // Tasa efectiva anual

                // Cálculo de la tasa de interés mensual i = (TEA / 12) / 100
                val i = (TEA / 12) / 100

                // Calcular la cuota mensual usando la fórmula
                val cuota = if (i != 0.0) {
                    VP * ((Math.pow(1 + i, n.toDouble()) * i) / (Math.pow(1 + i, n.toDouble()) - 1))
                } else {
                    VP / n // Caso especial: tasa de interés 0%
                }

                // Mostrar el resultado
                txtResultado.text = "Cuota mensual: %.2f".format(cuota)
            }
        }
    }
}