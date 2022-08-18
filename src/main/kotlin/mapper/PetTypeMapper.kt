package mapper

import nl.codestar.petclinic.generated.springkotlin.model.PetTypeDto
import nl.codestar.petclinic.generated.springkotlin.model.PetTypeFieldsDto
import nl.codestar.petclinic.model.PetType
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

/**
 * Map PetType & PetTypeDto using mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface PetTypeMapper {
	fun toPetType(petTypeDto: PetTypeDto?): PetType?
	fun toPetType(petTypeFieldsDto: PetTypeFieldsDto?): PetType?
	fun toPetTypeDto(petType: PetType?): PetTypeDto?
	fun toPetTypeDtos(petTypes: Collection<PetType?>?): List<PetTypeDto?>?
}