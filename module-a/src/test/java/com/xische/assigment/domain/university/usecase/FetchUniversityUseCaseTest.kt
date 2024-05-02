package com.xische.assigment.domain.university.usecase

import com.xische.assigment.domain.university.repository.FakeUniversityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchUniversityUseCaseTest {

    private lateinit var fetchUniversityUseCase: FetchUniversityUseCase
    private lateinit var fakeUniversityRepository: FakeUniversityRepository

    @Before
    fun setUp() {
        fakeUniversityRepository = FakeUniversityRepository()
        fetchUniversityUseCase = FetchUniversityUseCase(fakeUniversityRepository)
    }

    @Test
    operator fun invoke() {
        runBlocking {
            fetchUniversityUseCase.invoke()
        }
    }
}