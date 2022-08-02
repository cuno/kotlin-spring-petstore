package nl.codestar.springpetclinickotlin.api

import nl.codestar.springpetclinickotlin.model.PetDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class PetsApiDelegateImpl : PetsApiDelegate {
	override fun listPets(limit: Int?): ResponseEntity<List<PetDto>> = ResponseEntity(
		listOf(
			PetDto(1L, "Charlie"),
			PetDto(2L, "Obi"),
			PetDto(3L, "Loki"),
			PetDto(3L, "Cuno"),
		), HttpStatus.OK
	)

}