package com.swkang.model.domain.snappy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.exts.rx.subscribeAndDispose
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.base.ui.RvItem
import com.swkang.model.base.ui.TextCategoryItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * https://medium.com/over-engineering/detecting-snap-changes-with-androids-recyclerview-snaphelper-9e9f5e95c424
 */

data class Book(
    override val id: Long,
    val name: String,
    val author: String,
    val publisher: String,
    val image: String? = null,
    val description: String = ""
) : RvItem

class SnappyViewModel(
    val messageHelper: MessageHelper,
    val resourceHelper: ResourceHelper
) : BaseViewModel() {
    companion object {
        val MOCK_BOOK_ITEMS = listOf(
            TextCategoryItem(0, "베스트 셀러"),
            Book(1, "달러구트 꿈 백화점", "이미예", "팩토리나인", null, "소개 잠들어야만 입장 가능한 꿈 백화점에서 일어나는 비밀스럽고도 기묘하며 가슴 뭉클한 판타지 소설여기는 잠들어야만 입장할 수 있는 ‘달러구트 꿈 백화점’입니다. 잠들어야만 입장할 수 있는 독특한 마을. 그곳에 들어온 잠든 손님들에게 가장 인기+있는 곳은, 온갖 꿈을 한데 모아 판매하는 ‘달러구트의 꿈 백화점’이다. 긴 잠을 자는 사람들은 물론이고, 짧은 낮잠을 자는"),
            Book(2, "트렌드 코리아 2021 ", "김난도", "미래의 창", null, "소개 코로나가 앞당긴 미래, 더욱 빨라진 변화의 속도 바이러스發 경제, V-NOMICS(브이노믹스) 시대의 전략을 말하다‘집콕’이 일상어로 자리 잡고 비대면은 이제 누구에게나 익숙하며 마스크를 쓰지 않는 것이 더 어색한 세상이 되었다. 코로나가 일상이 되면서 사람들은 서서히 21세기 팬데믹에 적응해가는 중이다. 무슨 일이 벌어져도 삶은 계속되고 소비는 이루어진다"),
            Book(3, "어떻게 말해줘야 할까", "오은영", "김영사", null, "소개 채널A 〈요즘 육아 금쪽같은 내 새끼〉 SBS 〈우리 아이가 달라졌어요〉 국민 육아멘토 오은영 박사가 알려주는 ‘부모의 말’ 육아 현실을 200퍼센트 반영한 130가지 한마디‘국민 육아멘토’ ‘대한민국 엄마·아빠들의 엄마’ 오은영 박사의『어떻게 말해줘야 할까』. 쉽게 따라 할 수 있는 ‘부모의 말’을 친절하게 소개하는 육아서이다. 오은영 박사가 이전에 낸 책들과 비"),
            Book(4, "공정하다는 착각", "마이클 샌댈", "와이즈베리", null, "소개 마이클 샌델, 10여 년 만에 던지는 충격적 화두! “지금 서 있는 그 자리, 정말 당신의 능력 때문인가?” 마이클 샌델 10여년 만의 신간, 《공정하다는 착각》 출간! 샌델, 기울어진 사회구조 이면에 도사린 ‘능력주의의 덫’을 해체하다또 다시 ‘공정’이 화두다.언론 미디어를 통해, 부유층과 빈곤층, 청년과 장년, 정치인의 입을 통해 끊임없이 쏟아져 나온다. 기업은 정규"),
            Book(5, "미스터 마켓 2021", "이한영, 김효진", "페이지2", null, "소개 “2021년, 새로운 주도주를 준비하라!” 구독자 85만 명, 누적 조회 1억 5000만 회 경제 분야 압도적 1위 유튜브 「삼프로TV」와 함께한 투자 전망 프로젝트 2021년, 투자하기 전 반드시 읽어야 할 책 2020년은 그야말로 유례없는 주식 열풍이 분 해다. 사람들은 저축도 헐고, 때로는 부동산도 팔아서 주식에 뛰어들었고, 심지어는 대출까지 받아서 투자를 했다. 2020년"),
            TextCategoryItem(9999, "추천")
        )
    }

    private val _items = MutableLiveData<List<RvItem>>(emptyList())
    val items: LiveData<List<RvItem>>
        get() = _items

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        requestBookItems()
    }

    private fun requestBookItems() {
        Single.just(MOCK_BOOK_ITEMS)
            .delay((1..4L).random(), TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _isLoading.postValue(true) }
            .doFinally { _isLoading.postValue(false) }
            .subscribeAndDispose(
                this,
                {
                    _items.value = it
                }, {
                    messageHelper.showToast(
                        it.message ?: resourceHelper.getString(R.string.c_error)
                    )
                }
            )
    }

}