package com.example.hiltdemo.entity

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

//@Singleton
@ActivityScoped
class User @Inject constructor() {
}