package id.aryo.binarapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import id.aryo.binarapp.model.AndroidData
import id.aryo.binarapp.data.PreferenceHelper
import id.aryo.binarapp.toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent



class MainActivity : AppCompatActivity() {
    val listAndroid : List<AndroidData> = listOf(
        AndroidData("No Codename","1.0", "API Level 1"),
        AndroidData("Petit Four", "1.1", "API Level 2"),
        AndroidData("Cupcake","1.5", "API Level 3"),
        AndroidData("Donut", "1.6", "API Level 4"),
        AndroidData("Eclair","2.0-2.1", "API Level 5-7"),
        AndroidData("Froyo", "2.2-2.2.3", "API Level 8"),
        AndroidData("Gingerbread","2.3-2.3.7", "API Level 9-10"),
        AndroidData("Honeycomb", "3.0-3.2.6", "API Level 11-13"),
        AndroidData("Ice Cream Sandwich","4.0-4.0.4", "API Level 14-15"),
        AndroidData("Jelly Bean", "4.1-4.3.1", "API Level 16-18"),
        AndroidData("Kitkat","4.4-4.4.4", "API Level 19-20"),
        AndroidData("Lollipop", "5.0-5.1.1", "API Level 21-22"),
        AndroidData("Marshmallow","6.0-6.0.1", "API Level 23"),
        AndroidData("Nougat", "7.0-7.1.2", "API Level 24-25"),
        AndroidData("Oreo","8.0-8.1", "API Level 26-27"),
        AndroidData("Pie", "9.0", "API Level 28")
    )
    private val androidAdapter =
        id.aryo.binarapp.adapter.AndroidAdapter(listAndroid, this::onItemClick)

    private fun onItemClick(it: AndroidData) {
        toast("Anda memilih ${it.callName}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(id.aryo.binarapp.R.layout.activity_main)
        setupView()
        tv_logout.setOnClickListener {
            PreferenceHelper.clearLoggedInUser(getBaseContext())
            startActivity(Intent(baseContext, WelcomeActivity::class.java))
            finish()
        }
    }

    private fun setupView() {
        rvAndroid.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = androidAdapter
        }
    }
}
