package com.nadhif.hayazee.learndiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadhif.hayazee.learndiffutil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var noDiffUtilAdapter: NoDiffUtilAdapter
    private lateinit var withDiffUtilAdapter: WithDiffUtilAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        binding.btnSort.setOnClickListener {
            viewModel.sort()
        }

        viewModel.userList.observe(this) {
            noDiffUtilAdapter.setData(it)
            withDiffUtilAdapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {
        binding.apply {
            noDiffUtilAdapter = NoDiffUtilAdapter(viewModel)
            withDiffUtilAdapter = WithDiffUtilAdapter(viewModel)
            rvNoDiffUtil.adapter = noDiffUtilAdapter
            rvNoDiffUtil.layoutManager = LinearLayoutManager(this@MainActivity)
            rvWithDiffUtil.adapter = withDiffUtilAdapter
            rvWithDiffUtil.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}