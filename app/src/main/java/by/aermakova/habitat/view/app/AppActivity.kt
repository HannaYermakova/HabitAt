package by.aermakova.habitat.view.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.aermakova.habitat.R
import by.aermakova.habitat.databinding.AppActivityBinding
import by.aermakova.habitat.model.db.pref.Preferences
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AppActivity : AppCompatActivity(), HasSupportFragmentInjector {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).tryInjectAppActivity(this)
        super.onCreate(savedInstanceState)

        val binding = AppActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val controller = Navigation.findNavController(this, R.id.app_host_fragment)
        Navigation.setViewNavController(view, controller)
        if (Preferences.getUserName(this) != null) {
            controller.navigate(R.id.action_welcomeFlowFragment_to_mainFlowFragment)
        }
    }

    private var fragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = fragmentInjector

    @Inject
    fun injectDependencies(fragmentInjector: DispatchingAndroidInjector<Fragment>) {
        this.fragmentInjector = fragmentInjector
    }
}