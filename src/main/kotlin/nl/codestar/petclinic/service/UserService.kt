package nl.codestar.petclinic.service

import nl.codestar.petclinic.model.User
import nl.codestar.petclinic.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {
	@Autowired
	private val userRepository: UserRepository? = null
	@Transactional
	fun saveUser(user: User) {
		require(!(user.roles == null || user.roles.isEmpty())) { "User must have at least a role set!" }
		for (role in user.roles) {
			if (!role.name.startsWith("ROLE_")) {
				role.name = "ROLE_" + role.name
			}
			if (role.user == null) {
				role.user = user
			}
		}
		userRepository!!.save(user)
	}
}