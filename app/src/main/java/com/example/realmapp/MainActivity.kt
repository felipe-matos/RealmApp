package com.example.realmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.realmapp.database.DatabaseRealm
import com.example.realmapp.databinding.ActivityMainBinding
import com.example.realmapp.model.Usuario
import io.realm.kotlin.types.ObjectId


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
                idade = 10
            }

            realm.salvar(usuario)

        }

        binding.btnListar.setOnClickListener {

            val lista = realm.listar()

            var textoLista = ""
            lista.forEach {usuario ->
                textoLista += "${usuario.nome} - idade: ${usuario.idade}\n"
                Log.i("info_realm", "id${usuario.id}: ")
            }

            binding.textResultado.text = textoLista
        }

        binding.btnRemover.setOnClickListener {
            //65b15f001b8f2462d18ee33f


            val id = ObjectId.from("65b15f001b8f2462d18ee33f")
            realm.remover(id)


        }

        binding.btnAtualizar.setOnClickListener {

            //65b16a27fbf2330fe2d42e57
            val nomeRecuperado = binding.editNome.text.toString()
            val idSelecionado = ObjectId.from("65b16a27fbf2330fe2d42e57")
            val usuario = Usuario().apply {
                //id = idSelecionado
                nome = nomeRecuperado
                idade = 40
            }

            realm.atualizar(usuario)
        }

    }
}