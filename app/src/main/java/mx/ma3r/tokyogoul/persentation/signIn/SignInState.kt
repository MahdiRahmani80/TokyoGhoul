package mx.ma3r.tokyogoul.persentation.signIn

data class SignInState(
    val isSignInSuccessful:Boolean=false,
    val signInError:String?=null
)
