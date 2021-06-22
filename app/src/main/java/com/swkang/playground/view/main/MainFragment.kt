package com.swkang.playground.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.main.MainViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.main_fragment), View.OnClickListener {
    private val vm: MainViewModel by viewModels()

    override fun createViewModel(): BaseViewModel = vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)

        // TODO : 일단 아래 부분은 땜빵. 더 좋은 방법을 찾아서 적용 하도록 하자..
        // -> 원래 ViewModel에 inject되는 인스턴스에서 Activity를 필요로 했었다.
        // 하지만 이제 ViewModelScope에서는 Acitivty인스턴스를 주입 받을 수 없다.
        // 그렇기 떄문에 기본 ViewModel범위 내 주입되는 모듈에서 Activity를 사용 했다면 이를
        // Activity에 접근할 수 있는 모듈 단위로 변경 해야 한다.
        // 이렇게 바뀐 이유는 ViewModel의 라이프 사이클이 Acitivty나 Framgnet보다 길 수 있어서
        // 메모리 누수가 발생할 수 있어서 인거 같다.

        rootView?.findViewById<Button>(R.id.main_btn_covid19nav)?.setOnClickListener(this)
        rootView?.findViewById<Button>(R.id.main_btn_howmanypeoplesinspacenav)
            ?.setOnClickListener(this)
        rootView?.findViewById<Button>(R.id.main_btn_google_billingnav)?.setOnClickListener(this)

        return rootView
    }

    override fun onClick(view: View?) {
        if (view == null) return
        when (view.id) {
            R.id.main_btn_covid19nav -> onCovid19StatusBtnClicked()
            R.id.main_btn_howmanypeoplesinspacenav -> onHowManyPeoplesInSpaceBtnClicked()
            R.id.main_btn_google_billingnav -> onGoogleBillingBtnClicked()
        }
    }

    private fun onCovid19StatusBtnClicked() {
        val activity = getAppCompatActivity()
        val direction = MainFragmentDirections
            .actionMainFragmentToCovid19StatusActivity()
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
        activity.supportActionBar?.hide()
    }

    private fun onHowManyPeoplesInSpaceBtnClicked() {
        val activity = getAppCompatActivity()
        val direction = MainFragmentDirections
            .actionMainFragmentToHowManyPeopleInSpaceActivity()
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
        activity.supportActionBar?.hide()
    }

    private fun onGoogleBillingBtnClicked() {
        val activity = getAppCompatActivity()
    }

    private fun getAppCompatActivity(): AppCompatActivity =
        activity as AppCompatActivity

}