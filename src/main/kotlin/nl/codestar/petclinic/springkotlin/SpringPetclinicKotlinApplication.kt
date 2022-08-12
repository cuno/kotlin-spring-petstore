package nl.codestar.petclinic.springkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetclinicApplication

fun main(args: Array<String>) {
	runApplication<PetclinicApplication>(*args)
}
