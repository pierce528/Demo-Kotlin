package com.pierce.demo.list.ui.main

class HeaderItem(var title: String) : ListItem() {
    override fun getType(): Type {
       return ListItem.Type.HEADER;
    }

}
