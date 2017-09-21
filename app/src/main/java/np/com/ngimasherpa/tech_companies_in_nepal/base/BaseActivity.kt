package np.com.ngimasherpa.tech_companies_in_nepal.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by ngima on 9/19/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract var layout: Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        init();
    }

    protected fun getContext() = this
}