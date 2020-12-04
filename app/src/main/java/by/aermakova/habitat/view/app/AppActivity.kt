package by.aermakova.habitat.view.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.AppActivityBinding
import by.aermakova.habitat.model.Preferences

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = AppActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val controller = Navigation.findNavController(this, R.id.app_host_fragment)
        Navigation.setViewNavController(view, controller)
        if (Preferences.instance?.getUserName(this) != null) {
            controller.navigate(R.id.action_welcomeFlowFragment_to_mainFlowFragment)
        }
    }
}