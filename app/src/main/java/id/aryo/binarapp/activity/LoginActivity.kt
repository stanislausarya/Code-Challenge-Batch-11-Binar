package id.aryo.binarapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.EditText
import android.view.KeyEvent
import android.view.View
import id.aryo.binarapp.data.PreferenceHelper


class LoginActivity : AppCompatActivity() {

    private var mViewEmail: EditText? = null
    private var mViewPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(id.aryo.binarapp.R.layout.activity_login)

        mViewEmail = findViewById(id.aryo.binarapp.R.id.et_emailLogin)
        mViewPassword = findViewById(id.aryo.binarapp.R.id.et_passwordLogin)

        mViewPassword!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia()
                    return true
                }
                return false
            }
        })

        btn_loginEnd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                razia()
            }
        })

        ib_backLogin.setOnClickListener {
            startActivity(Intent(baseContext, WelcomeActivity::class.java))
        }
    }

    private fun razia() {
        mViewEmail!!.error = null
        mViewPassword!!.error = null
        var fokus: View? = null
        var cancel = false

        val email = mViewEmail!!.text.toString()
        val password = mViewPassword!!.text.toString()

        if (TextUtils.isEmpty(email)) {
            mViewEmail!!.error = "This field is required"
            fokus = mViewEmail
            cancel = true
        } else if (!cekEmail(email)) {
            mViewEmail!!.error = "This Username is not found"
            fokus = mViewEmail
            cancel = true
        }

        if (TextUtils.isEmpty(password)) {
            mViewPassword!!.error = "This field is required"
            fokus = mViewPassword
            cancel = true
        } else if (!cekPassword(password)) {
            mViewPassword!!.error = "This password is incorrect"
            fokus = mViewPassword
            cancel = true
        }

        if (cancel)
            fokus!!.requestFocus()
        else
            masuk()
    }

    private fun masuk() {
        PreferenceHelper.setLoggedInUser(baseContext, PreferenceHelper.getRegisteredUser(baseContext))
        PreferenceHelper.setLoggedInStatus(baseContext, true)
        startActivity(Intent(baseContext, MainActivity::class.java))
    }

    private fun cekPassword(password: String): Boolean {
        return password == PreferenceHelper.getRegisteredPass(baseContext)
    }

    private fun cekEmail(email: String): Boolean {
        return email == PreferenceHelper.getRegisteredEmail(baseContext)
    }
}
