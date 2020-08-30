package developer.jimlyas.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import developer.jimlyas.sample.R.string
import developer.jimlyas.secand.Secand
import developer.jimlyas.secand.Secand.SecurityCheck.EMULATOR
import developer.jimlyas.secand.Secand.SecurityCheck.ROOTED
import kotlinx.android.synthetic.main.activity_sample.btnClose

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        when (Secand.check(this)) {
            EMULATOR -> closeCauseNotSafe(getString(string.label_device_emulator))
            ROOTED -> closeCauseNotSafe("device is rooted")
            else -> {
                Log.i("INFO", getString(string.label_device_safe))
            }
        }

        btnClose.setOnClickListener { closeCauseNotSafe("Thanks") }
    }

    private fun closeCauseNotSafe(reason: String) {
        Toast.makeText(this, reason, Toast.LENGTH_SHORT).show()
        finishAffinity()
    }
}