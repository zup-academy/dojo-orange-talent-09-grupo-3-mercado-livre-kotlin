package br.com.zup.edu.mercadolivrekotlin.usuario

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Usuario(
    val login: String,
     senha: String) {

    @Id
    @GeneratedValue
    var id : Long ?= null

    val instanteCadastro = LocalDateTime.now()

    var senha = BCryptPasswordEncoder().encode(senha)

}
