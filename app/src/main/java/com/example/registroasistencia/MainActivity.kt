package com.example.registroasistencia

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var tvMensaje: TextView
    private lateinit var etxtNombre: EditText
    private lateinit var etxtMatricula: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnTemaIJS: Button

    private var oscuroIJS = false
    private var contadorAsistentesIJS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMensaje = findViewById(R.id.tvMensaje)
        etxtNombre = findViewById(R.id.etxtNombre)
        etxtMatricula = findViewById(R.id.etxtMatricula)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnTemaIJS = findViewById(R.id.btnTema)

        oscuroIJS = when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO -> false
            else -> resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK ==
                android.content.res.Configuration.UI_MODE_NIGHT_YES
        }
        actualizarTextoTemaIJS()

        btnRegistrar.setOnClickListener { registrarAsistenteIJS() }
        btnLimpiar.setOnClickListener { limpiarIJS() }
        btnTemaIJS.setOnClickListener { cambiarTemaIJS() }
    }

    private fun registrarAsistenteIJS() {
        val nombre = etxtNombre.text.toString().trim()
        val matricula = etxtMatricula.text.toString().trim()

        if (nombre.isEmpty()) {
            etxtNombre.error = getString(R.string.val_nombre_vacioIJS)
            Toast.makeText(this, getString(R.string.val_nombre_vacioIJS), Toast.LENGTH_SHORT).show()
            etxtNombre.requestFocus()
            return
        }
        if (nombre.length < 3) {
            etxtNombre.error = getString(R.string.val_nombre_cortoIJS)
            Toast.makeText(this, getString(R.string.val_nombre_cortoIJS), Toast.LENGTH_SHORT).show()
            etxtNombre.requestFocus()
            return
        }
        if (matricula.isEmpty()) {
            etxtMatricula.error = getString(R.string.val_matricula_vaciaIJS)
            Toast.makeText(this, getString(R.string.val_matricula_vaciaIJS), Toast.LENGTH_SHORT).show()
            etxtMatricula.requestFocus()
            return
        }
        if (matricula.length != 7) {
            etxtMatricula.error = getString(R.string.val_matricula_largoIJS)
            Toast.makeText(this, getString(R.string.val_matricula_largoIJS), Toast.LENGTH_SHORT).show()
            etxtMatricula.requestFocus()
            return
        }

        contadorAsistentesIJS++
        tvMensaje.text = getString(
            R.string.msg_registradoIJS,
            nombre,
            matricula,
            contadorAsistentesIJS
        )
        btnRegistrar.text = getString(R.string.btn_registrar_otroIJS)
        etxtNombre.error = null
        etxtMatricula.error = null
        Toast.makeText(this, getString(R.string.btn_registrarIJS) + " ✓", Toast.LENGTH_SHORT).show()
    }

    private fun limpiarIJS() {
        etxtNombre.text.clear()
        etxtMatricula.text.clear()
        etxtNombre.error = null
        etxtMatricula.error = null
        tvMensaje.text = getString(R.string.msg_inicialIJS)
        btnRegistrar.text = getString(R.string.btn_registrarIJS)
        etxtNombre.requestFocus()
    }

    private fun cambiarTemaIJS() {
        oscuroIJS = !oscuroIJS
        AppCompatDelegate.setDefaultNightMode(
            if (oscuroIJS) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        actualizarTextoTemaIJS()
    }

    private fun actualizarTextoTemaIJS() {
        btnTemaIJS.text = getString(
            if (oscuroIJS) R.string.btn_tema_claroIJS
            else R.string.btn_tema_oscuroIJS
        )
    }
}
