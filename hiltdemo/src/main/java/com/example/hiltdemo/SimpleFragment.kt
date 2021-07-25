package com.example.hiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hiltdemo.entity.MyContext
import com.example.hiltdemo.entity.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SimpleFragment : Fragment() {

    @Inject
    lateinit var user: User

    @Inject
    lateinit var myContext: MyContext

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("Fragment: $user")

        log("Fragment : ${myContext.conext}  ${myContext.activityContext}")

    }
}