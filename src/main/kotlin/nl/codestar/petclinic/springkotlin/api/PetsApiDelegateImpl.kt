package nl.codestar.petclinic.springkotlin.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import mapper.PetMapperImpl
import nl.codestar.petclinic.generated.springkotlin.api.PetsApiDelegate
import nl.codestar.petclinic.generated.springkotlin.model.PetDto
import nl.codestar.petclinic.generated.springkotlin.model.PetTypeDto
import nl.codestar.petclinic.model.Visit
import nl.codestar.petclinic.service.ClinicService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class PetsApiDelegateImpl @Autowired constructor(
	private val clinicService: ClinicService, private val petMapperImpl: PetMapperImpl
) : PetsApiDelegate {
	private val logger = LoggerFactory.getLogger(PetsApiDelegateImpl::class.java)

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

	override suspend fun addPet(petDto: PetDto): ResponseEntity<PetDto> {
		val pet = petMapperImpl.toPet(petDto)
		val httpStatus = try {
			pet?.let {
				clinicService.savePet(it)
				CREATED
			}
		} catch (e: Exception) {
			logger.error(e.toString())
			INTERNAL_SERVER_ERROR
		} ?: BAD_REQUEST

		return ResponseEntity(petDto, httpStatus)
	}

	override suspend fun deletePet(petId: Int) = ResponseEntity<PetDto>(clinicService.run {
		try {
			findPetById(petId)?.let {
				deletePet(it)
				OK
			}.also {
				logger.debug("Pet deleted", it)
			} ?: NOT_FOUND
		} catch (e: DataAccessException) {
			logger.error(e.toString())
			INTERNAL_SERVER_ERROR
		}
	}

	)

}