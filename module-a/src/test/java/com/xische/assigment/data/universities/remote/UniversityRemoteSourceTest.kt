package com.xische.assigment.data.universities.remote

import com.xische.assigment.data.common.exception.NoInternetConnectionException
import com.xische.assigment.data.universities.remote.api.UniversityApi
import com.xische.assigment.data.universities.remote.dto.UniversityResponse
import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.dummyList
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class UniversityRemoteSourceTest {
    private val mockApi = mock<UniversityApi>()
    private val universityRemoteSource = UniversityRemoteSource(mockApi)

    @Test
    fun `fetchUniversity should return Success when API is successful`() = runTest {
        // Arrange
        val apiResponse = dummyList()
        val response = Response.success(apiResponse)
        whenever(mockApi.getUniversityData()).thenReturn(response)
        // Act
        val result = universityRemoteSource.fetchUniversity()
        // Assert
        assertTrue(result is BaseResult.Success)
        assertEquals(1, (result as BaseResult.Success).data.size)
    }

    @Test
    fun `fetchUniversity should return Error when there's a NoInternetConnectionException`() =
        runTest {
            // Arrange
            whenever(mockApi.getUniversityData()).thenAnswer {
                throw NoInternetConnectionException()
            }
            // Act
            val result = universityRemoteSource.fetchUniversity()

            // Assert
            assertTrue(result is BaseResult.Error)
            assertEquals(0, (result as BaseResult.Error).err.code)
            assertEquals("You are offline", result.err.message)
        }

    @Test
    fun `fetchUniversity should return Error when API fails with HTTP error`() = runTest {
        // Arrange
        val response = Response.error<List<UniversityResponse>>(
            404,
            okhttp3.ResponseBody.create(null, "Not Found")
        )

        whenever(mockApi.getUniversityData()).thenReturn(response)
        // Act
        val result = universityRemoteSource.fetchUniversity()

        // Assert
        assertTrue(result is BaseResult.Error)
        assertEquals(404, (result as BaseResult.Error).err.code)
    }

    @Test
    fun `fetchUniversity should return Error on general exception`() = runTest {
        // Arrange
        whenever(mockApi.getUniversityData()).thenAnswer {
            throw Exception("Something went wrong")
        }
        // Act
        val result = universityRemoteSource.fetchUniversity()
        // Assert
        assertTrue(result is BaseResult.Error)
        assertEquals(-1, (result as BaseResult.Error).err.code)
        assertEquals("Something went wrong", result.err.message)
    }


}