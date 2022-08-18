package mapper

import nl.codestar.petclinic.generated.springkotlin.model.SpecialtyDto
import nl.codestar.petclinic.model.Specialty
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

/**
 * Map Specialty & SpecialtyDto using mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface SpecialtyMapper {
	fun toSpecialty(specialtyDto: SpecialtyDto?): Specialty?
	fun toSpecialtyDto(specialty: Specialty?): SpecialtyDto?
	fun toSpecialtyDtos(specialties: Collection<Specialty?>?): Collection<SpecialtyDto?>?
	fun toSpecialtys(specialties: Collection<SpecialtyDto?>?): Collection<Specialty?>?
}