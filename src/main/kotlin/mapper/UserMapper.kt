package mapper

import nl.codestar.petclinic.generated.springkotlin.model.RoleDto
import nl.codestar.petclinic.generated.springkotlin.model.UserDto
import nl.codestar.petclinic.model.Role
import nl.codestar.petclinic.model.User
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

/**
 * Map User/Role & UserDto/RoleDto using mapstruct
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UserMapper {
	fun toRole(roleDto: RoleDto?): Role?
	fun toRoleDto(role: Role?): RoleDto?
	fun toRoleDtos(roles: Collection<Role?>?): Collection<RoleDto?>?
	fun toUser(userDto: UserDto?): User?
	fun toUserDto(user: User?): UserDto?
	fun toRoles(roleDtos: Collection<RoleDto?>?): Collection<Role?>?
}