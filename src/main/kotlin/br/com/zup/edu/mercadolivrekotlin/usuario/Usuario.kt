package br.com.zup.edu.mercadolivrekotlin.usuario

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Usuario(login: String, senha: String) {

    @Id
    @GeneratedValue
    var id : Long ?= null

    val instanteCadastro = LocalDateTime.now()


}
