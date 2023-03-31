package kr.swkang.pokemon

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.palette.graphics.Palette
import kr.swkang.core.domain.pokemon.model.SimplePokemonInfos
import kr.swkang.design.components.OnLoadingProgressWithDim
import kr.swkang.design.extensions.getBitmapFromDrawable

/**
 * 포켓몬 목록 화면
 *
 * @author bmo
 * @since 2023-03-12
 */

@Composable
fun PokeScreen(
    viewModel: PokeViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading: Boolean by viewModel.isLoading.collectAsState(initial = false)
    val pokemons = viewModel.getPokemons().collectAsLazyPagingItems()
    PokeScreenDetails(
        pokemons = pokemons,
        isLoading = isLoading,
        modifier = modifier
    )
}

@Composable
fun PokeScreenDetails(
    pokemons: LazyPagingItems<SimplePokemonInfos>,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = pokemons,
                key = { it.id }
            ) { pokemon ->
                if (pokemon == null) return@items
                SimplePokemonCard(
                    pokemons = pokemon
                )
            }
        }

        // 포켓몬 목록 첫번째 로드
        when (val state = pokemons.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    state.error.message?.ifEmpty { "Unknown error occure." },
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoadState.Loading -> {
                OnLoadingProgressWithDim()
            }
            else -> {
                // nothing to do..
            }
        }

        // 포켓못 목록의 페이징 ui 처리
        when (val state = pokemons.loadState.append) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    state.error.message?.ifEmpty { "Unknown error occure." },
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoadState.Loading -> {
                OnLoadingProgressWithDim()
            }
            else -> {
                // nothing to do..
            }
        }

        if (isLoading) {
            OnLoadingProgressWithDim()
        }
    }
}

@Composable
fun SimplePokemonCard(
    pokemons: SimplePokemonInfos?,
    modifier: Modifier = Modifier
) {
    if (pokemons == null) return
    val context = LocalContext.current
    val pokemonSpriteResId = POKEMON_SPRITES[pokemons.id] ?: R.drawable._poke_placeholder
    val bitmap = remember { context.getBitmapFromDrawable(pokemonSpriteResId) }
    val palette = bitmap?.let {
        remember {
            Palette.from(bitmap).generate()
        }
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(start = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = palette?.lightVibrantSwatch?.let {
                Color(it.rgb)
            } ?: MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                modifier = Modifier
                    .width(140.dp)
                    .fillMaxHeight()
                    .padding(10.dp),
                painter = painterResource(id = pokemonSpriteResId),
                contentDescription = "pokemon background image",
                alignment = Alignment.Center,
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            ) {
                Text(
                    text = pokemons.name,
                    fontSize = 28.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.ExtraBold,
                    color = palette?.lightVibrantSwatch?.let {
                        Color(it.titleTextColor)
                    } ?: Color.White
                )
                Text(
                    text = "(${pokemons.id})",
                    fontSize = 18.sp,
                    maxLines = 1,
                    color = palette?.vibrantSwatch?.let {
                        Color(it.titleTextColor)
                    } ?: Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun SimplePokemonCardPreview() {
    SimplePokemonCard(
        SimplePokemonInfos(
            "asdf zxcv",
            2,
            "https://google.com"
        )
    )
}

@Preview
@Composable
fun PokeScreenDetailsPreView() {
    // PlayGroundTheme {
    //     PokeScreenDetails(
    //         isLoading = true
    //     )
    // }
}

internal val POKEMON_SPRITES = mapOf(
    1 to R.drawable._1,
    2 to R.drawable._2,
    3 to R.drawable._3,
    4 to R.drawable._4,
    5 to R.drawable._5,
    6 to R.drawable._6,
    7 to R.drawable._7,
    8 to R.drawable._8,
    9 to R.drawable._9,
    10 to R.drawable._10,
    11 to R.drawable._11,
    12 to R.drawable._12,
    13 to R.drawable._13,
    14 to R.drawable._14,
    15 to R.drawable._15,
    16 to R.drawable._16,
    17 to R.drawable._17,
    18 to R.drawable._18,
    19 to R.drawable._19,
    20 to R.drawable._20,
    21 to R.drawable._21,
    22 to R.drawable._22,
    23 to R.drawable._23,
    24 to R.drawable._24,
    25 to R.drawable._25,
    26 to R.drawable._26,
    27 to R.drawable._27,
    28 to R.drawable._28,
    29 to R.drawable._29,
    30 to R.drawable._30,
    31 to R.drawable._31,
    32 to R.drawable._32,
    33 to R.drawable._33,
    34 to R.drawable._34,
    35 to R.drawable._35,
    36 to R.drawable._36,
    37 to R.drawable._37,
    38 to R.drawable._38,
    39 to R.drawable._39,
    40 to R.drawable._40,
    41 to R.drawable._41,
    42 to R.drawable._42,
    43 to R.drawable._43,
    44 to R.drawable._44,
    45 to R.drawable._45,
    46 to R.drawable._46,
    47 to R.drawable._47,
    48 to R.drawable._48,
    49 to R.drawable._49,
    50 to R.drawable._50,
    51 to R.drawable._51,
    52 to R.drawable._52,
    53 to R.drawable._53,
    54 to R.drawable._54,
    55 to R.drawable._55,
    56 to R.drawable._56,
    57 to R.drawable._57,
    58 to R.drawable._58,
    59 to R.drawable._59,
    60 to R.drawable._60,
    61 to R.drawable._61,
    62 to R.drawable._62,
    63 to R.drawable._63,
    64 to R.drawable._64,
    65 to R.drawable._65,
    66 to R.drawable._66,
    67 to R.drawable._67,
    68 to R.drawable._68,
    69 to R.drawable._69,
    70 to R.drawable._70,
    71 to R.drawable._71,
    72 to R.drawable._72,
    73 to R.drawable._73,
    74 to R.drawable._74,
    75 to R.drawable._75,
    76 to R.drawable._76,
    77 to R.drawable._77,
    78 to R.drawable._78,
    79 to R.drawable._79,
    80 to R.drawable._80,
    81 to R.drawable._81,
    82 to R.drawable._82,
    83 to R.drawable._83,
    84 to R.drawable._84,
    85 to R.drawable._85,
    86 to R.drawable._86,
    87 to R.drawable._87,
    88 to R.drawable._88,
    89 to R.drawable._89,
    90 to R.drawable._90,
    91 to R.drawable._91,
    92 to R.drawable._92,
    93 to R.drawable._93,
    94 to R.drawable._94,
    95 to R.drawable._95,
    96 to R.drawable._96,
    97 to R.drawable._97,
    98 to R.drawable._98,
    99 to R.drawable._99,
    100 to R.drawable._100,
    101 to R.drawable._101,
    102 to R.drawable._102,
    103 to R.drawable._103,
    104 to R.drawable._104,
    105 to R.drawable._105,
    106 to R.drawable._106,
    107 to R.drawable._107,
    108 to R.drawable._108,
    109 to R.drawable._109,
    110 to R.drawable._110,
    111 to R.drawable._111,
    112 to R.drawable._112,
    113 to R.drawable._113,
    114 to R.drawable._114,
    115 to R.drawable._115,
    116 to R.drawable._116,
    117 to R.drawable._117,
    118 to R.drawable._118,
    119 to R.drawable._119,
    120 to R.drawable._120,
    121 to R.drawable._121,
    122 to R.drawable._122,
    123 to R.drawable._123,
    124 to R.drawable._124,
    125 to R.drawable._125,
    126 to R.drawable._126,
    127 to R.drawable._127,
    128 to R.drawable._128,
    129 to R.drawable._129,
    130 to R.drawable._130,
    131 to R.drawable._131,
    132 to R.drawable._132,
    133 to R.drawable._133,
    134 to R.drawable._134,
    135 to R.drawable._135,
    136 to R.drawable._136,
    137 to R.drawable._137,
    138 to R.drawable._138,
    139 to R.drawable._139,
    140 to R.drawable._140,
    141 to R.drawable._141,
    142 to R.drawable._142,
    143 to R.drawable._143,
    144 to R.drawable._144,
    145 to R.drawable._145,
    146 to R.drawable._146,
    147 to R.drawable._147,
    148 to R.drawable._148,
    149 to R.drawable._149,
    150 to R.drawable._150,
    151 to R.drawable._151,
    152 to R.drawable._152,
    153 to R.drawable._153,
    154 to R.drawable._154,
    155 to R.drawable._155,
    156 to R.drawable._156,
    157 to R.drawable._157,
    158 to R.drawable._158,
    159 to R.drawable._159,
    160 to R.drawable._160,
    161 to R.drawable._161,
    162 to R.drawable._162,
    163 to R.drawable._163,
    164 to R.drawable._164,
    165 to R.drawable._165,
    166 to R.drawable._166,
    167 to R.drawable._167,
    168 to R.drawable._168,
    169 to R.drawable._169,
    170 to R.drawable._170,
    171 to R.drawable._171,
    172 to R.drawable._172,
    173 to R.drawable._173,
    174 to R.drawable._174,
    175 to R.drawable._175,
    176 to R.drawable._176,
    177 to R.drawable._177,
    178 to R.drawable._178,
    179 to R.drawable._179,
    180 to R.drawable._180,
    181 to R.drawable._181,
    182 to R.drawable._182,
    183 to R.drawable._183,
    184 to R.drawable._184,
    185 to R.drawable._185,
    186 to R.drawable._186,
    187 to R.drawable._187,
    188 to R.drawable._188,
    189 to R.drawable._189,
    190 to R.drawable._190,
    191 to R.drawable._191,
    192 to R.drawable._192,
    193 to R.drawable._193,
    194 to R.drawable._194,
    195 to R.drawable._195,
    196 to R.drawable._196,
    197 to R.drawable._197,
    198 to R.drawable._198,
    199 to R.drawable._199,
    200 to R.drawable._200,
    201 to R.drawable._201,
    202 to R.drawable._202,
    203 to R.drawable._203,
    204 to R.drawable._204,
    205 to R.drawable._205,
    206 to R.drawable._206,
    207 to R.drawable._207,
    208 to R.drawable._208,
    209 to R.drawable._209,
    210 to R.drawable._210,
    211 to R.drawable._211,
    212 to R.drawable._212,
    213 to R.drawable._213,
    214 to R.drawable._214,
    215 to R.drawable._215,
    216 to R.drawable._216,
    217 to R.drawable._217,
    218 to R.drawable._218,
    219 to R.drawable._219,
    220 to R.drawable._220,
    221 to R.drawable._221,
    222 to R.drawable._222,
    223 to R.drawable._223,
    224 to R.drawable._224,
    225 to R.drawable._225,
    226 to R.drawable._226,
    227 to R.drawable._227,
    228 to R.drawable._228,
    229 to R.drawable._229,
    230 to R.drawable._230,
    231 to R.drawable._231,
    232 to R.drawable._232,
    233 to R.drawable._233,
    234 to R.drawable._234,
    235 to R.drawable._235,
    236 to R.drawable._236,
    237 to R.drawable._237,
    238 to R.drawable._238,
    239 to R.drawable._239,
    240 to R.drawable._240,
    241 to R.drawable._241,
    242 to R.drawable._242,
    243 to R.drawable._243,
    244 to R.drawable._244,
    245 to R.drawable._245,
    246 to R.drawable._246,
    247 to R.drawable._247,
    248 to R.drawable._248,
    249 to R.drawable._249,
    250 to R.drawable._250,
    251 to R.drawable._251,
    252 to R.drawable._252,
    253 to R.drawable._253,
    254 to R.drawable._254,
    255 to R.drawable._255,
    256 to R.drawable._256,
    257 to R.drawable._257,
    258 to R.drawable._258,
    259 to R.drawable._259,
    260 to R.drawable._260,
    261 to R.drawable._261,
    262 to R.drawable._262,
    263 to R.drawable._263,
    264 to R.drawable._264,
    265 to R.drawable._265,
    266 to R.drawable._266,
    267 to R.drawable._267,
    268 to R.drawable._268,
    269 to R.drawable._269,
    270 to R.drawable._270,
    271 to R.drawable._271,
    272 to R.drawable._272,
    273 to R.drawable._273,
    274 to R.drawable._274,
    275 to R.drawable._275,
    276 to R.drawable._276,
    277 to R.drawable._277,
    278 to R.drawable._278,
    279 to R.drawable._279,
    280 to R.drawable._280,
    281 to R.drawable._281,
    282 to R.drawable._282,
    283 to R.drawable._283,
    284 to R.drawable._284,
    285 to R.drawable._285,
    286 to R.drawable._286,
    287 to R.drawable._287,
    288 to R.drawable._288,
    289 to R.drawable._289,
    290 to R.drawable._290,
    291 to R.drawable._291,
    292 to R.drawable._292,
    293 to R.drawable._293,
    294 to R.drawable._294,
    295 to R.drawable._295,
    296 to R.drawable._296,
    297 to R.drawable._297,
    298 to R.drawable._298,
    299 to R.drawable._299,
    300 to R.drawable._300
)
