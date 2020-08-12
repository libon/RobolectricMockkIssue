package com.example.robolectricmockk

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    private lateinit var foo: Foo

    @Before
    fun setUp() {
        foo = mockk(relaxed = true)
    }

    @Test
    fun testMockk1() {
        assertEquals(0, foo.hello.length)
    }

    @Config(sdk=[28])
    @Test
    fun testMockk2() {
        assertEquals(0, foo.hello.length)
    }
}