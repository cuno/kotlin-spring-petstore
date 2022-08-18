package mapper

import nl.codestar.petclinic.generated.springkotlin.model.VisitDto
import nl.codestar.petclinic.generated.springkotlin.model.VisitFieldsDto
import nl.codestar.petclinic.model.Visit
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

/**
 * Map Visit & VisitDto using mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = [PetMapper::class])
interface VisitMapper {
	fun toVisit(visitDto: VisitDto?): Visit?
	fun toVisit(visitFieldsDto: VisitFieldsDto?): Visit?

	@Mapping(source = "pet.id", target = "petId")
	fun toVisitDto(visit: Visit?): VisitDto?
	fun toVisitsDto(visits: Collection<Visit?>?): Collection<VisitDto?>?
}