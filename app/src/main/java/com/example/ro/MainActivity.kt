package com.example.ro

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ro.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = Adapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        viewModel.setupDatabase(applicationContext)

        viewModel.filteredData.observe(this) { data ->
            val convertedData = data.map { character ->
                ResultsItem(
                    name = character.name,
                    height = character.height,
                    birthYear = character.birthYear,
                    gender = character.gender
                )
            }
            adapter.submitList(convertedData)
        }

        viewModel.fetchData()

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.filterData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}