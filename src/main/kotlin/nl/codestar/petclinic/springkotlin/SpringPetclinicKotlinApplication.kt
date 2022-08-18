package nl.codestar.petclinic.springkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(
	scanBasePackages = ["nl.codestar.petclinic", "mapper"]
)
@EnableJpaRepositories("nl.codestar.petclinic.model", "nl.codestar.petclinic.repository")
@EntityScan("nl.codestar.petclinic.model")
@EnableTransactionManagement
class PetclinicApplication

fun main(args: Array<String>) {
	runApplication<PetclinicApplication>(*args)
}
