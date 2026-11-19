package com.example.controlesentrada

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.controlesentrada.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taller_seleccionado: String
    private lateinit var turno_seleccionado: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inicializamos el spinner
        val datos = arrayOf("Programación Android","Diseño de interfaces","Redes")

        val adaptador =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, datos
            )
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adaptador

        //Al pulsar el botón de inscripción
        binding.btnInscribirse.setOnClickListener {
            if(binding.checkBox.isChecked) {
                binding.tVisor.text = Html.fromHtml(
                    "<p style='color:blue;'><b>Inscripción realizada</b></p>",
                    Html.FROM_HTML_MODE_LEGACY
                )

                binding.tVisor.append("\n" + binding.tNombre.text + " - " + binding.tCorreo.text)
                binding.tVisor.append("\n" + taller_seleccionado + " - " + turno_seleccionado)
            }
        }

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            val lblTextView: TextView = findViewById(R.id.textView)
            override fun onNothingSelected(parent: AdapterView<*>) {
                taller_seleccionado = "Sin selección"
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val pos = parent.getItemAtPosition(position)
                taller_seleccionado = "$pos"
            }
        }

        binding.rgTurno.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.rbManana ->
                    turno_seleccionado = "Mañana"
                R.id.rbTarde ->
                    turno_seleccionado = "Tarde"
            }

        }



    }
}