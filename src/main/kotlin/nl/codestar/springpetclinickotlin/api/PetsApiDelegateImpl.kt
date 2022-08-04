package nl.codestar.springpetclinickotlin.api

import nl.codestar.springpetclinickotlin.model.PetDto
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

val database = listOf(
	PetDto(0L, "Charlie"),
	PetDto(1L, "Obi"),
	PetDto(2L, "Loki"),
	PetDto(3L, "Cuno"),
)

@Component
class PetsApiDelegateImpl : PetsApiDelegate {
	override fun listPets(limit: Int?): ResponseEntity<List<PetDto>> = ResponseEntity(
		limit?.let { database.take(it) } ?: database, OK
	)

	override fun showPetById(petId: String): ResponseEntity<PetDto> =
		database.find { it.id == petId.toLong() }?.let {
			ResponseEntity(
				it, OK
			)
		} ?: throw ResponseStatusException(
			NOT_FOUND, "entity not found"
		)
}