package br.com.zup.edu.mercadolivrekotlin.usuario

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.util.UriBuilder

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureDataJpa
class CadastrarUsuarioControllerTest{

    @Autowired
    lateinit var objectMapper : ObjectMapper

    @Autowired
    lateinit var mockMvc: MockMvc

    val uri = "/usuarios"

    @Test
    fun deveRetornar200SeCadastroOk() {
        val novoUsuario: NovoUsuarioRequest = NovoUsuarioRequest("teste@zup.com", "123456")
        val request = objectMapper.writeValueAsString(novoUsuario)
        val consultaRequest: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(uri)
            .content(request)
            .contentType(MediaType.APPLICATION_JSON)
        mockMvc.perform(consultaRequest)
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


//    @ParameterizedTest
//    @NullAndEmptySource
//    @ValueSource(strings = {"invalidEmail.com", "@invalid.com", "@.com", "@invalid"})
    @Test
    fun deveRetornar400CasoEmailInvalido() {
        val novoUsuario: NovoUsuarioRequest = NovoUsuarioRequest("teste", "123456")
        val request = objectMapper.writeValueAsString(novoUsuario)
        val consultaRequest: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(uri)
            .content(request)
            .contentType(MediaType.APPLICATION_JSON)
        mockMvc.perform(consultaRequest)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
    @Test
    fun deveRetornar400CasoEmailEmBranco() {
        val novoUsuario: NovoUsuarioRequest = NovoUsuarioRequest("", "123456")
        val request = objectMapper.writeValueAsString(novoUsuario)
        val consultaRequest: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(uri)
            .content(request)
            .contentType(MediaType.APPLICATION_JSON)
        mockMvc.perform(consultaRequest)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun deveRetornar400CasoSenhaComMenosDe6Digitos() {
        val novoUsuario: NovoUsuarioRequest = NovoUsuarioRequest("teste@zup.com", "1234")
        val request = objectMapper.writeValueAsString(novoUsuario)
        val consultaRequest: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(uri)
            .content(request)
            .contentType(MediaType.APPLICATION_JSON)
        mockMvc.perform(consultaRequest)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
    @Test
    fun deveRetornar400CasoSenhaEmBranco() {
        val novoUsuario: NovoUsuarioRequest = NovoUsuarioRequest("teste@zup.com", "")
        val request = objectMapper.writeValueAsString(novoUsuario)
        val consultaRequest: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(uri)
            .content(request)
            .contentType(MediaType.APPLICATION_JSON)
        mockMvc.perform(consultaRequest)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}