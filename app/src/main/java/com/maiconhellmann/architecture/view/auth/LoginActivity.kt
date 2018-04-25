package com.maiconhellmann.architecture.view.auth

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.linkedin.platform.APIHelper
import com.linkedin.platform.LISessionManager
import com.linkedin.platform.errors.LIApiError
import com.linkedin.platform.errors.LIAuthError
import com.linkedin.platform.listeners.ApiListener
import com.linkedin.platform.listeners.ApiResponse
import com.linkedin.platform.listeners.AuthListener
import com.linkedin.platform.utils.Scope
import com.maiconhellmann.architecture.R
import com.maiconhellmann.architecture.view.BaseActivity
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber
import java.security.MessageDigest


class LoginActivity : BaseActivity() {

    var callbackManager: CallbackManager? = null

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFacebook()
        initTwitter()
        initGoogle()
        initLinkedin()
    }

    private fun initFacebook() {
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        setContentView(R.layout.activity_login)

        callbackManager = CallbackManager.Factory.create()

        signinFacebook.setReadPermissions("public_profile", "email", "user_birthday", "user_location")
        // Callback registration
        signinFacebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
                Timber.d(loginResult.accessToken.token)
            }

            override fun onCancel() {
                // App code
                Timber.d("login canceled")
            }

            override fun onError(exception: FacebookException) {
                // App code
                Timber.e(exception)
            }
        })
    }

    private fun initTwitter() {
        twitterButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                val session = TwitterCore.getInstance().sessionManager.activeSession
                val authToken = session.authToken
                val token = authToken.token
                val secret = authToken.secret

                Timber.d("authToken $authToken")
                Timber.d("token $token")
                Timber.d("secret $secret")

                val authClient = TwitterAuthClient()
                authClient.requestEmail(session, object : Callback<String>() {
                    override fun success(result: Result<String>) {
                        Timber.d(result.data)
                    }

                    override fun failure(exception: TwitterException) {
                        Timber.e(exception)
                    }
                })
            }

            override fun failure(exception: TwitterException?) {
                Timber.e(exception)
            }
        }
    }

    private fun initGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.GOOGLE_SERVER_CLIENT_ID))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        googleButton.setSize(SignInButton.SIZE_STANDARD)

        googleButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient?.signInIntent
            startActivityForResult(signInIntent, 1)
        }

        checkForUpdates()
    }

    private fun initLinkedin() {
//        generateHashkey()

        signinLinkedin.setOnClickListener {
            val scope = Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS)
            LISessionManager.getInstance(this).init(this, scope, object : AuthListener {
                override fun onAuthSuccess() {
                    Timber.d("Linkedin accessToken: ${LISessionManager.getInstance(applicationContext).session.accessToken.value}")

                    val url = "https://api.linkedin.com/v1/people/~"

                    val apiHelper = APIHelper.getInstance(applicationContext)
                    apiHelper.getRequest(applicationContext, url, object : ApiListener {
                        override fun onApiSuccess(apiResponse: ApiResponse) {
                            // Success!
                            Timber.d("Linkedin status code: ${apiResponse.statusCode}")
                        }

                        override fun onApiError(liApiError: LIApiError) {
                            // Error making GET request!
                            Timber.e(liApiError)
                        }
                    })
                }

                override fun onAuthError(error: LIAuthError) {
                    Timber.e(error.toString())
                }
            }, true)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        twitterButton.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
        LISessionManager.getInstance(this).onActivityResult(this, requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account.email
            account.displayName
            account.idToken
            // Signed in successfully, show authenticated UI.
//            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Timber.e(e)
//            updateUI(null)
        }

    }

    private fun checkForUpdates() {
        val availability = GoogleApiAvailability.getInstance()
        val resultCode = availability.isGooglePlayServicesAvailable(this)
        if (resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {
            availability.getErrorDialog(this, resultCode, 3).show()
        }
    }

    fun generateHashkey() {
        val info = packageManager.getPackageInfo("com.maiconhellmann.architecture", PackageManager.GET_SIGNATURES)
        info.signatures.forEach {
            val md = MessageDigest.getInstance("SHA")
            md.update(it.toByteArray())
            Timber.d("Package ${info.packageName}")
            Timber.d("SHA ${Base64.encodeToString(md.digest(), Base64.NO_WRAP)}")
        }
    }
}