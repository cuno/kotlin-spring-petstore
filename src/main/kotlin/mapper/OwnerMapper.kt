package mapper

import nl.codestar.petclinic.generated.springkotlin.model.OwnerDto
import nl.codestar.petclinic.generated.springkotlin.model.OwnerFieldsDto
import nl.codestar.petclinic.model.Owner
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

/**
 * Maps Owner & OwnerDto using Mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = [PetMapper::class])
interface OwnerMapper {
	fun toOwnerDto(owner: Owner?): OwnerDto?
	fun toOwner(ownerDto: OwnerDto?): Owner?
	fun toOwner(ownerDto: OwnerFieldsDto?): Owner?
	fun toOwnerDtoCollection(ownerCollection: Collection<Owner?>?): List<OwnerDto?>?
	fun toOwners(ownerDtos: Collection<OwnerDto?>?): Collection<Owner?>?
}