package com.example.realmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.realmapp.database.DatabaseRealm
import com.example.realmapp.databinding.ActivityMainBinding
import com.example.realmapp.model.Usuario

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val realm by lazy {
        DatabaseRealm()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {

            val nomeRecuperado = binding.editNome.text.toString()

            val usuario = Usuario().apply {
                nome = nomeRecuperado
                idade = 30
            }

            realm.salvar(usuario)

        }

        binding.btnListar.setOnClickListener {

            val lista = realm.listar()

            var textoLista = ""
            lista.forEach {usuario ->
                textoLista += "${usuario.nome} - idade: ${usuario.idade}\n"
            }

            binding.textResultado.text = textoLista
        }

    }
}