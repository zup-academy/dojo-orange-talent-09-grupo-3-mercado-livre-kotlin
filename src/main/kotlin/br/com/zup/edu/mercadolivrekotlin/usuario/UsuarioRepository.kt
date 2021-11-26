package br.com.zup.edu.mercadolivrekotlin.usuario

import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}