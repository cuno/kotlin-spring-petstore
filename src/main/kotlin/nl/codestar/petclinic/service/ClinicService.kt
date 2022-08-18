/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.codestar.petclinic.service

import nl.codestar.petclinic.model.*
import nl.codestar.petclinic.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.orm.ObjectRetrievalFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Mostly used as a facade for all Petclinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
@Service
class ClinicService @Autowired constructor(
	private val petRepository: PetRepository,
	private val vetRepository: VetRepository,
	private val ownerRepository: OwnerRepository,
	private val visitRepository: VisitRepository,
	private val specialtyRepository: SpecialtyRepository,
	private val petTypeRepository: PetTypeRepository
) {
	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findAllPets(): Collection<Pet> = petRepository.findAll()

	@Transactional
	@Throws(DataAccessException::class)
	fun deletePet(pet: Pet) {
		petRepository.delete(pet)
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findVisitById(visitId: Int): Visit? {
		var visit: Visit? = null
		visit = try {
			visitRepository.findById(visitId).orElse(null)
		} catch (e: ObjectRetrievalFailureException) {
			// just ignore not found exceptions for Jdbc/Jpa realization
			return null
		} catch (e: EmptyResultDataAccessException) {
			return null
		}
		return visit
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findAllVisits(): Collection<Visit> {
		return visitRepository.findAll()
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun deleteVisit(visit: Visit) {
		visitRepository.delete(visit)
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findVetById(id: Int): Vet? {
		var vet: Vet? = null
		vet = try {
			vetRepository.findById(id).orElse(null)
		} catch (e: ObjectRetrievalFailureException) {
			// just ignore not found exceptions for Jdbc/Jpa realization
			return null
		} catch (e: EmptyResultDataAccessException) {
			return null
		}
		return vet
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findAllVets(): Collection<Vet> {
		return vetRepository.findAll()
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun saveVet(vet: Vet) {
		vetRepository.save(vet)
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun deleteVet(vet: Vet) {
		vetRepository.delete(vet)
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findAllOwners(): Collection<Owner> {
		return ownerRepository.findAll()
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun deleteOwner(owner: Owner) {
		ownerRepository.delete(owner)
	}

	@Transactional(readOnly = true)
	fun findPetTypeById(petTypeId: Int): PetType? {
		var petType: PetType? = null
		petType = try {
			petTypeRepository.findById(petTypeId).orElse(null)
		} catch (e: ObjectRetrievalFailureException) {
			// just ignore not found exceptions for Jdbc/Jpa realization
			return null
		} catch (e: EmptyResultDataAccessException) {
			return null
		}
		return petType
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findAllPetTypes(): Collection<PetType> {
		return petTypeRepository.findAll()
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun savePetType(petType: PetType) {
		petTypeRepository.save(petType)
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun deletePetType(petType: PetType) {
		petTypeRepository.delete(petType)
	}

	@Transactional(readOnly = true)
	fun findSpecialtyById(specialtyId: Int): Specialty? {
		var specialty: Specialty? = null
		specialty = try {
			specialtyRepository.findById(specialtyId).orElse(null)
		} catch (e: ObjectRetrievalFailureException) {
			// just ignore not found exceptions for Jdbc/Jpa realization
			return null
		} catch (e: EmptyResultDataAccessException) {
			return null
		}
		return specialty
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findAllSpecialties(): Collection<Specialty> {
		return specialtyRepository.findAll()
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun saveSpecialty(specialty: Specialty) {
		specialtyRepository.save(specialty)
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun deleteSpecialty(specialty: Specialty) {
		specialtyRepository.delete(specialty)
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findPetTypes(): Collection<PetType>? {
		return petRepository.findPetTypes()
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findOwnerById(id: Int): Owner? {
		var owner: Owner? = null
		owner = try {
			ownerRepository.findById(id).orElse(null)
		} catch (e: ObjectRetrievalFailureException) {
			// just ignore not found exceptions for Jdbc/Jpa realization
			return null
		} catch (e: EmptyResultDataAccessException) {
			return null
		}
		return owner
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findPetById(id: Int): Pet? {
		var pet: Pet? = null
		pet = try {
			petRepository.findById(id).orElse(null)
		} catch (e: ObjectRetrievalFailureException) {
			// just ignore not found exceptions for Jdbc/Jpa realization
			return null
		} catch (e: EmptyResultDataAccessException) {
			return null
		}
		return pet
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun savePet(pet: Pet) {
		petRepository.save(pet)
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun saveVisit(visit: Visit) {
		visitRepository.save(visit)
	}

	@Transactional(readOnly = true)
	@Cacheable(value = ["vets"])
	@Throws(
		DataAccessException::class
	)
	fun findVets(): Collection<Vet> {
		return vetRepository.findAll()
	}

	@Transactional
	@Throws(DataAccessException::class)
	fun saveOwner(owner: Owner) {
		ownerRepository.save(owner)
	}

	@Transactional(readOnly = true)
	@Throws(DataAccessException::class)
	fun findOwnerByLastName(lastName: String): Collection<Owner> {
		return ownerRepository.findByLastName(lastName)
	}

	@Transactional(readOnly = true)
	fun findVisitsByPetId(petId: Int): Collection<Visit> {
		return visitRepository.findByPetId(petId)
	}
}