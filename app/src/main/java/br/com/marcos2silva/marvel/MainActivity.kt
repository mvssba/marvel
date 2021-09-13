package br.com.marcos2silva.marvel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.marcos2silva.marvel.characters.presentation.CharactersFragment
import br.com.marcos2silva.marvel.characters.presentation.CharactersViewModel
import br.com.marcos2silva.marvel.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}