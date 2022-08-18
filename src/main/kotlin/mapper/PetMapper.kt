package mapper

import nl.codestar.petclinic.generated.springkotlin.model.PetDto
import nl.codestar.petclinic.generated.springkotlin.model.PetFieldsDto
import nl.codestar.petclinic.generated.springkotlin.model.PetTypeDto
import nl.codestar.petclinic.model.Pet
import nl.codestar.petclinic.model.PetType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

/**
 * Map Pet & PetDto using mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface PetMapper {
	@Mapping(source = "owner.id", target = "ownerId")
	fun toPetDto(pet: Pet?): PetDto?
	fun toPetsDto(pets: Collection<Pet?>?): Collection<PetDto?>?
	fun toPets(pets: Collection<PetDto?>?): Collection<Pet?>?
	fun toPet(petDto: PetDto?): Pet?
	fun toPet(petFieldsDto: PetFieldsDto?): Pet?
	fun toPetTypeDto(petType: PetType?): PetTypeDto?
	fun toPetType(petTypeDto: PetTypeDto?): PetType?
	fun toPetTypeDtos(petTypes: Collection<PetType?>?): Collection<PetTypeDto?>?
}