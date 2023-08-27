package com.skeleton.app.feature.posts.data.remote.datasource

import com.skeleton.app.feature.posts.data.remote.api.UserDetailsApi
import com.skeleton.app.feature.posts.data.remote.model.Gender
import com.skeleton.app.feature.posts.data.remote.model.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class UserDetailsDataSourceTest {

    @Mock
    private lateinit var api: UserDetailsApi

    private lateinit var userDataSource: IUserDetailsDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userDataSource = UserDetailsDataSource(api)
    }

    @Test
    fun `test getUserDetails() function`() = runBlocking {
        val response = User(1000L, "name", "email", Gender.male, "")
        val expectedResult = Result.success(response)
        whenever(api.getUserDetails(1000)).thenAnswer { response }

        TestCase.assertEquals(userDataSource.getUserDetails(1000), expectedResult)
    }
}