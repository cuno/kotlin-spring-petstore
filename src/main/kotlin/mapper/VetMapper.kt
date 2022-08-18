package mapper

import nl.codestar.petclinic.generated.springkotlin.model.VetDto
import nl.codestar.petclinic.generated.springkotlin.model.VetFieldsDto
import nl.codestar.petclinic.model.Vet
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

/**
 * Map Vet & VetoDto using mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = [SpecialtyMapper::class])
interface VetMapper {
	fun toVet(vetDto: VetDto?): Vet?
	fun toVet(vetFieldsDto: VetFieldsDto?): Vet?
	fun toVetDto(vet: Vet?): VetDto?
	fun toVetDtos(vets: Collection<Vet?>?): Collection<VetDto?>?
}