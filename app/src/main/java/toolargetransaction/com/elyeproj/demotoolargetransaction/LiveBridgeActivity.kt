package toolargetransaction.com.elyeproj.demotoolargetransaction

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.livefront.bridge.Bridge
import kotlinx.android.synthetic.main.activity_too_large_transaction.*

class LiveBridgeActivity : AppCompatActivity() {

    companion object {
        const val KEY = "KeyIndex"
        const val SHARED_TAG = "com.livefront.bridge.BridgeDelegate"
    }

    private var index = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_too_large_transaction)

        title = "Live Bridge Transaction"

        if (savedInstanceState == null) {
            Bridge.clearAll(applicationContext)
        } else {
            index = savedInstanceState.getInt(KEY)
        }

        sharedPreferences = applicationContext.getSharedPreferences(SHARED_TAG, Context.MODE_PRIVATE)
        updateSharedPrefCount()


        btn_add_state_fragment.setOnClickListener {
            index++
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, LiveBridgeFragment.newInstance(index))
                    .addToBackStack(LiveBridgeFragment.TAG)
                    .commit()
        }

        btn_add_empty_fragment.setOnClickListener {
            index++
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, NothingFragment())
                    .addToBackStack(NothingFragment.TAG)
                    .commit()
        }
    }

    private fun updateSharedPrefCount() {
        txt_shared_pref_size.text = sharedPreferences.all.size.toString()
    }

    override fun onBackPressed() {
        index--
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(KEY, index)
    }

}