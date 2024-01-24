package com.example.realmapp.database

import com.example.realmapp.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.ObjectId

class DatabaseRealm {

    private val configuracao = RealmConfiguration.Builder(
        schema = setOf(Usuario::class)
    )

    private val realm = Realm.open(configuracao.build())

    fun salvar(usuario: Usuario){

        realm.writeBlocking {
            copyToRealm(usuario)
        }

    }

    fun listar() : RealmResults<Usuario>{

        return  realm
        //query<Usuario>( "nome = $0","felipe")
            .query<Usuario>()
            //.sort("nome",Sort.ASCENDING)
            .find()


    }

    fun remover(id: io.realm.kotlin.types.ObjectId){

        realm.writeBlocking {
            val usuarioAltualizar = query<Usuario>("id == $0" , id)
                .find()
                .first()

            delete( usuarioAltualizar)
        }

    }


    fun atualizar(usuario: Usuario){

        realm.writeBlocking {
            val usuarioAtualizar = query<Usuario>("id == $0" , usuario.id)
                .find()
                .first()

            usuarioAtualizar.nome = usuario.nome
            usuarioAtualizar.idade= usuario.idade
        }

    }
}