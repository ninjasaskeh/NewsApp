package com.example.newsapp.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.fahru.beritaapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.rvadapter.ItemRvMainAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(fragmentHomeBinding.homeToolbar)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterRV = ItemRvMainAdapter()
        fragmentHomeBinding.run {
            homeToolbar.title = "Detik.com"
            fragmentHomeRv.apply {
                setHasFixedSize(true)
                adapter = adapterRV
                layoutManager = LinearLayoutManager(context)
            }
        }
        homeViewModel.fetchAPI("id", "")
        homeViewModel.getData().observe(viewLifecycleOwner, Observer {
            adapterRV.addData(it)
        })
    }
}