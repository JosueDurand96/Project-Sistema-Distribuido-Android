package com.durand.vacunacionperu.ui.users

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.user.UserResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.campaign.CampaignAdapter
import com.durand.vacunacionperu.ui.campaign.CampaignState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.fragment_campaign.*
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : BaseFragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        userRecyclerView = root.findViewById(R.id.userRecyclerView)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userViewModel.state.observe(::getLifecycle, ::getUser)
        userViewModel.getUser()
        userProgressBar.visibility = View.VISIBLE
    }



    private fun getUser(screenState: ScreenState<UserState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: UserState) {
        when (renderState) {
            is UserState.ShowSuccess -> {
                userProgressBar.visibility = View.GONE
                userList(renderState.reg)
            }
            is UserState.ShowError -> {

                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    private fun userList(list: List<UserResponseModel>){
        userAdapter = UserAdapter(context as Activity, list)
        userRecyclerView.adapter = userAdapter
        userRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }
}