package nl.codestar.petclinic.springkotlin.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import nl.codestar.petclinic.springkotlin.model.PetDto
import nl.codestar.petclinic.springkotlin.model.PetTypeDto
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

val database = listOf(
	PetDto("Charlie", LocalDate.EPOCH, PetTypeDto("cat"), 0),
	PetDto("Obi", LocalDate.EPOCH, PetTypeDto("jedi"), 1),
	PetDto("Loki", LocalDate.EPOCH, PetTypeDto("god"), 2),
	PetDto("Cuno", LocalDate.EPOCH, PetTypeDto("developer"), 3),
)

@Component
class PetsApiDelegateImpl : PetsApiDelegate {
	override fun listPets(): ResponseEntity<Flow<PetDto>> = ResponseEntity(database.asFlow(), OK)

	override suspend fun getPet(petId: Int): ResponseEntity<PetDto> = database.find { it.id == petId }?.let {
		ResponseEntity(
			it, OK
		)
	} ?: throw ResponseStatusException(
		NOT_FOUND, "entity not found"
	)
}