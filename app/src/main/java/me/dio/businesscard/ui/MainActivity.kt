package me.dio.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import me.dio.businesscard.databinding.ActivityMainBinding


import me.dio.businesscard.App
import me.dio.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardApapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvBusinessCard.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener(){
        binding.fabAddCard.setOnClickListener {
            val intent = Intent (this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listinerShare = { card ->
            Image.share(this@MainActivity, card)
        }

    }

    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) { businessCards ->
            adapter.submitList(businessCards)

        }
    }
}