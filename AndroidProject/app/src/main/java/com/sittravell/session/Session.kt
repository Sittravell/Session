
/*
    REPLACE: With your package id
    IMPORT: User
 */
package com.sittravell.session
import android.app.Activity

object Session{
    val TAG = "Session"
    const val NO_USERNAME = "NO_USERNAME"
    const val NO_NAME = "*NO_NAME*"
    const val LOGGED_IN = 1
    const val LOGGED_OUT = 2

    /* STORAGE KEYS */
    val statusK = "status"
    val usernameK = "username"
    val nameK = "name"

    var status = LOGGED_OUT

    // Initialize username with NO_USERNAME. When username is set, it also stores the username in Storage
    var username: String = NO_USERNAME
        set(value) {
            field = value
            storage.set(usernameK, value)
        }

    var name: String = NO_NAME
        set(value) {
            field = value
            storage.set(nameK, value)
        }


    lateinit var storage: Storage

    val isLoggedIn: Boolean
        get(){
            return status == LOGGED_IN
        }

    /* NOTE: Needs to be executed on every activity that uses it. This is to initialize Storage. */
    /* NOTE: If activity extends SuperActivity, activity does not need to setup */
    fun setup(activity: Activity){
        if (!Session::storage.isInitialized) {
            storage = Storage(activity)
            status = storage.get(statusK) ?: LOGGED_OUT
            name = storage.get(nameK) ?: NO_NAME
            username = storage.get(usernameK) ?: NO_USERNAME
        }
    }

    /*
        Call this when you want to login
     */
    fun start(username: String){
        Session.username = username
        status = LOGGED_IN

        storage.set(statusK, LOGGED_IN)
        storage.set(usernameK, username)
    }

    /*
        Use when log out. Here you reset the information. Set Session
        variables to null and information stored in storage to another value.
        It is easier to set the storage values to something else than removing them.
     */
    fun finish(){
        username = NO_USERNAME
        name = NO_NAME
        status = LOGGED_OUT

        // Set to another value
        storage.set(statusK, LOGGED_OUT)
        storage.set(usernameK, NO_USERNAME)
        storage.set(nameK, NO_NAME)
    }
}

