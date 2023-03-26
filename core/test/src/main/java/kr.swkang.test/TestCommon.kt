package kr.swkang.core.test

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame

/**
 * @author bmo
 * @since 2023-03-26
 */

infix fun <T : Any> T?.shouldBe(expected: T?) = this.apply { assertEquals(expected, this) }

infix fun <T : Any> T?.shouldNotBe(expected: T?) = this.apply { assertNotSame(expected, this) }
