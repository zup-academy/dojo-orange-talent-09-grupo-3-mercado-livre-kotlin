package br.com.zup.edu.mercadolivrekotlin.common

import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass


class UniqueValueValidator : ConstraintValidator<UniqueValue, Any> {
    private var domainAttribute: String? = null
    private var klass: KClass<Any>? = null

    @PersistenceContext
    private val manager: EntityManager? = null
    override fun initialize(params: UniqueValue) {
        domainAttribute = params.fieldName
        klass = params.domainClass
    }

    override fun isValid(value: Any, context: ConstraintValidatorContext): Boolean {
        val query: Query =
            manager!!.createQuery("select 1 from " + klass!!.qualifiedName + " Where " + domainAttribute + "=:value")
        query.setParameter("value", value)
        val list: List<*> = query.getResultList()
        Assert.state(
            list.size <= 1,
            "Foi encontrado mais de um " + klass + "com o atributo " + domainAttribute + " = " + value
        )
        return list.isEmpty()
    }
}