package com.example.lib1

import org.junit.Test

import org.junit.Assert.*

class UnitTest {
  @Test
  fun `call internal class`() {
    InternalClass.hello()
  }
}
