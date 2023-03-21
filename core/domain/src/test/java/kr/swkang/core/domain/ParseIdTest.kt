package kr.swkang.core.domain

import java.net.URL
import org.junit.Test

/**
 */
class ParseIdTest {
    @Test
    fun parseId_fromUrl() {
        val path = URL("https://pokeapi.co/api/v2/pokemon/14/").path
        // todo : path 에서 마지막 /14/ 이거 숫자만 파싱해서 얻어야 한다.
    }
}
