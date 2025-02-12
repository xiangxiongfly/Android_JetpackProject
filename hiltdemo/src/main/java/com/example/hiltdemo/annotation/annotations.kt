package com.example.hiltdemo.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class File

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Db