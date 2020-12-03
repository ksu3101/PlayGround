package com.swkang.model.domain.covid19

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.domain.covid19.datas.Covid19Infos
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos
import com.swkang.model.domain.covid19.repository.Covid19Repository
import com.swkang.model.given
import com.swkang.model.hasCalled
import com.swkang.model.hasNotCalled
import com.swkang.model.mock
import com.swkang.model.shouldBe
import com.swkang.model.shouldNotBe
import com.swkang.model.willReturn
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import java.lang.Exception
import java.lang.RuntimeException

class Covid19StatusViewModelTest {
    companion object {
        private val DUMMY_COVID19INFOS = Covid19Infos(
            1,
            12345,
            99,
            897,
            0,
            55555,
            5,
            listOf(
                LocationCovid19Infos(0, 22, "aaa", 1, 2, 3),
                LocationCovid19Infos(1, 22, "bbb", 1, 2, 3),
                LocationCovid19Infos(2, 22, "ccc", 1, 2, 3)
            )
        )
    }

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repo: Covid19Repository = mock()
    private val messageHelper: MessageHelper = mock()
    private val resourceHelper: ResourceHelper = mock()
    private lateinit var vm: Covid19StatusViewModel

    @Before
    fun setUp() {
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        vm = Covid19StatusViewModel(repo, messageHelper, resourceHelper)
    }

    @Test
    fun createdViewModel_OnStartUp_ThenClearedViews() {
        vm.currentTimeStamp.value shouldBe ""
        vm.totalCase.value shouldBe 0
        vm.todayNewCase.value shouldBe 0
        vm.totalDeath.value shouldBe 0
        vm.todayNewDeath.value shouldBe 0
        vm.totalRecovered.value shouldBe 0
        vm.todayNewRecovered.value shouldBe 0
        vm.covid19Countries.value shouldNotBe null
        vm.covid19Countries.value!!.isEmpty() shouldBe true
    }

    @Test
    fun loadCorrectCovid19KoreanStatus_OnStartUp_ThenUpdateViews() {
        val isKorea = true
        given(repo.requestKoreaStatus()) willReturn Single.just(DUMMY_COVID19INFOS)

        vm.setCountryAndStartRequesting(isKorea)

        repo.hasCalled().requestKoreaStatus()
        repo.hasNotCalled().requestWorldStatusSummary()
        messageHelper.hasNotCalled().showToast(anyString(), anyBoolean())
        vm.isKorean.value shouldBe isKorea
        vm.isLoading.value shouldBe false
        vm.totalCase.value shouldBe DUMMY_COVID19INFOS.totalConfirmed
        vm.todayNewCase.value shouldBe DUMMY_COVID19INFOS.newConfirmed
        vm.totalDeath.value shouldBe DUMMY_COVID19INFOS.totalDeath
        vm.todayNewDeath.value shouldBe DUMMY_COVID19INFOS.newDeath
        vm.totalRecovered.value shouldBe DUMMY_COVID19INFOS.totalRecovered
        vm.todayNewRecovered.value shouldBe DUMMY_COVID19INFOS.newRecovered
        vm.covid19Countries.value shouldNotBe null
        vm.covid19Countries.value!!.size shouldBe 4 // add Top header
    }

    @Test
    fun loadCovid19KoreanStatusButErrorOccured_OnStartUp_ThenShowingToastErrorMessage() {
        val isKorea = true
        given(repo.requestKoreaStatus()) willReturn Single.error<Exception>(RuntimeException("blah blah"))

        vm.setCountryAndStartRequesting(isKorea)

        repo.hasCalled().requestKoreaStatus()
        repo.hasNotCalled().requestWorldStatusSummary()
        resourceHelper.hasNotCalled().getString(anyInt())
        messageHelper.hasCalled().showToast(anyString(), anyBoolean())
        vm.isKorean.value shouldBe isKorea
        vm.isLoading.value shouldBe false
        vm.totalCase.value shouldBe 0
        vm.covid19Countries.value!!.isEmpty() shouldBe true
    }

    @Test
    fun loadCovid19KoreanStatusButErrorOccured_OnStartUp_ThenShowingToastCommonErrorMessage() {
        val isKorea = true
        given(resourceHelper.getString(anyInt())) willReturn "blah blah blah"
        given(repo.requestKoreaStatus()) willReturn Single.error<Exception>(RuntimeException(""))

        vm.setCountryAndStartRequesting(isKorea)

        repo.hasCalled().requestKoreaStatus()
        repo.hasNotCalled().requestWorldStatusSummary()
        resourceHelper.hasCalled().getString(anyInt())
        messageHelper.hasCalled().showToast(anyString(), anyBoolean())
        vm.isKorean.value shouldBe isKorea
        vm.isLoading.value shouldBe false
        vm.totalCase.value shouldBe 0
        vm.covid19Countries.value!!.isEmpty() shouldBe true
    }

    @Test
    fun loadCorrectWorldOfCovid19StatusInfo_OnStartUp_ThenUpdateViews() {
        val isKorea = false
        given(repo.requestWorldStatusSummary()) willReturn Single.just(DUMMY_COVID19INFOS)

        vm.setCountryAndStartRequesting(isKorea)

        repo.hasNotCalled().requestKoreaStatus()
        repo.hasCalled().requestWorldStatusSummary()
        messageHelper.hasNotCalled().showToast(anyString(), anyBoolean())
        vm.isKorean.value shouldBe isKorea
        vm.isLoading.value shouldBe false
        vm.totalCase.value shouldBe DUMMY_COVID19INFOS.totalConfirmed
        vm.todayNewCase.value shouldBe DUMMY_COVID19INFOS.newConfirmed
        vm.totalDeath.value shouldBe DUMMY_COVID19INFOS.totalDeath
        vm.todayNewDeath.value shouldBe DUMMY_COVID19INFOS.newDeath
        vm.totalRecovered.value shouldBe DUMMY_COVID19INFOS.totalRecovered
        vm.todayNewRecovered.value shouldBe DUMMY_COVID19INFOS.newRecovered
        vm.covid19Countries.value shouldNotBe null
        vm.covid19Countries.value!!.size shouldBe 4 // add Top header
    }

}