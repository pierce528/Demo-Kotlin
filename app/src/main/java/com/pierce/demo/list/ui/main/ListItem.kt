package com.pierce.demo.list.ui.main

abstract class ListItem {

    abstract fun getType() : Type

    enum class Type private constructor(val d:Int){
        HEADER(0), PASS(1)
    }
}
