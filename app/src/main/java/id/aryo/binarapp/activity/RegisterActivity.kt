package id.aryo.binarapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.aryo.binarapp.R
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.EditText
import id.aryo.binarapp.data.PreferenceHelper
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private var mViewUser: EditText? = null
    private var mViewEmail: EditText? = null
    private var mViewOrganization: EditText? = null
    private var mViewPassword: EditText? = null
    private var mViewRepassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mViewUser = findViewById(R.id.et_registerName)
        mViewEmail = findViewById(R.id.et_registerEmail)
        mViewOrganization = findViewById(R.id.et_registerDomain)
        mViewPassword = findViewById(R.id.et_registerPassword)
        mViewRepassword = findViewById(R.id.et_checkPassword)

        mViewRepassword!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia()
                    return true
                }
                return false
            }
        })

        btn_register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                razia()
            }
        })

        ib_backRegister.setOnClickListener {
            startActivity(Intent(baseContext, WelcomeActivity::class.java))
            finish()
        }
    }
    private fun razia() {
        mViewUser!!.error = null
        mViewEmail!!.error = null
        mViewOrganization!!.error = null
        mViewPassword!!.error = null
        mViewRepassword!!.error = null
        var fokus: View? = null
        var cancel = false

        val repassword = mViewRepassword!!.text.toString()
        val user = mViewUser!!.text.toString()
        val password = mViewPassword!!.text.toString()
        val email = mViewEmail!!.text.toString()
        val domain = mViewOrganization!!.text.toString()

        if (TextUtils.isEmpty(email)) {
            mViewUser!!.error = "This field is required"
            fokus = mViewUser
            cancel = true
        } else if (cekEmail(email)) {
            mViewUser!!.error = "This Email is already registered"
            fokus = mViewUser
            cancel = true
        }

        if (TextUtils.isEmpty(password)) {
            mViewPassword!!.error = "This field is required"
            fokus = mViewPassword
            cancel = true
        } else if (!cekPassword(password, repassword)) {
            mViewRepassword!!.error = "This password is incorrect"
            fokus = mViewRepassword
            cancel = true
        }

        if (cancel) {
            fokus!!.requestFocus()
        } else {
            PreferenceHelper.setRegisteredEmail(baseContext, email)
            PreferenceHelper.setRegisteredPass(baseContext, password)
            PreferenceHelper.setRegisteredDomain(baseContext, domain)
            PreferenceHelper.setRegisteredUser(baseContext, user)
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun cekPassword(password: String, repassword: String): Boolean {
        return password == repassword
    }

    private fun cekEmail(email: String): Boolean {
        return email == PreferenceHelper.getRegisteredEmail(baseContext)
    }
}