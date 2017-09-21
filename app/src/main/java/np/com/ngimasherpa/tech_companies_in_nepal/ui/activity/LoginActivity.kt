package np.com.ngimasherpa.tech_companies_in_nepal.ui.activity

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*
import np.com.ngimasherpa.tech_companies_in_nepal.R
import np.com.ngimasherpa.tech_companies_in_nepal.base.BaseActivity

class LoginActivity : BaseActivity() {
    override var layout = R.layout.activity_login

    override fun init() {

        text_sign_in.setOnClickListener { navigateToMain() }
    }

    private fun navigateToMain() {
        startActivity(MainActivity.setupIntent(this))
        finish()
    }
}
