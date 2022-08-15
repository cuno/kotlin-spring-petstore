package nl.codestar.petclinic.repository

import nl.codestar.petclinic.model.User
import org.springframework.dao.DataAccessException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
	@Throws(DataAccessException::class)
	override fun <S : User> save(entity: S): S
}