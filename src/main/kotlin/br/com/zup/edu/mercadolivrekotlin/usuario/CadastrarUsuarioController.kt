package br.com.zup.edu.mercadolivrekotlin.usuario

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class CadastrarUsuarioController(val usuarioRepository: UsuarioRepository) {

    @PostMapping
    fun cadastra(@RequestBody @Valid request: NovoUsuarioRequest): ResponseEntity<Any> {

        val novoUsuario = request.toModel()

        usuarioRepository.save(novoUsuario)

      return ResponseEntity.ok().build()
    }
}