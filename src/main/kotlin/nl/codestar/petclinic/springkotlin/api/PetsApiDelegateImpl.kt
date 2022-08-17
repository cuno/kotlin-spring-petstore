package nl.codestar.petclinic.springkotlin.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import nl.codestar.petclinic.generated.springkotlin.api.PetsApiDelegate
import nl.codestar.petclinic.generated.springkotlin.model.PetDto
import nl.codestar.petclinic.generated.springkotlin.model.PetTypeDto
import nl.codestar.petclinic.model.Pet
import nl.codestar.petclinic.service.ClinicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class PetsApiDelegateImpl @Autowired constructor(private val clinicService: ClinicService) : PetsApiDelegate {
	override fun listPets(): ResponseEntity<Flow<PetDto>> = ResponseEntity(
		clinicService.findAllPets().asFlow().map { PetDto(it.name, it.birthDate, PetTypeDto(it.type.name)) }, OK
	)

	override suspend fun getPet(petId: Int): ResponseEntity<PetDto> = clinicService.findPetById(petId)?.let {
		ResponseEntity(
			PetDto(it.name, it.birthDate, PetTypeDto(it.type.name)), OK
		)
	} ?: throw ResponseStatusException(
		NOT_FOUND, "entity not found"
	)

	override suspend fun addPet(petDto: PetDto): ResponseEntity<PetDto> = try {
		val newPet = Pet()
		newPet.name = petDto.name
		newPet.birthDate = petDto.birthDate
		newPet.type = petDto.type.id?.let { clinicService.findPetTypeById(it) }
		newPet.owner = petDto.ownerId?.let { clinicService.findOwnerById(it) }
		clinicService.savePet(newPet)
		ResponseEntity(petDto, CREATED)
	} catch (e: Exception) {
		ResponseEntity(INTERNAL_SERVER_ERROR)
	}
}