package com.skeleton.app.feature.posts.data.repository

import com.skeleton.app.feature.posts.data.remote.datasource.IUserDetailsDataSource
import com.skeleton.app.feature.posts.data.remote.model.Gender
import com.skeleton.app.feature.posts.data.remote.model.User
import com.skeleton.app.feature.posts.domain.repository.IUserDetailsRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class UserDetailsRepositoryTest{

    @Mock
    private lateinit var dataSource: IUserDetailsDataSource

    private lateinit var repository: IUserDetailsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = UserDetailsRepository(dataSource)
    }

    @Test
    fun `test getUserDetails() function`() = runBlocking {
        val response = User(1000L, "name", "email", Gender.male, "")
        val expectedResult = Result.success(response)
        whenever(dataSource.getUserDetails(1000)).thenAnswer { response }

        TestCase.assertEquals(repository.getUserDetails(1000), expectedResult)
    }
}