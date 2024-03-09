package com.luis.wishlist

class ItemFetcher {
    companion object {
        private val names = ""
        private val prices = ""
        private val urls = ""

        fun getEmails(): MutableList<Item> {
            var items : MutableList<Item> = ArrayList()
            return items
        }

        fun addItem(name: String, price: String, url:String): Item {
            var item:Item = Item(name,price,url)
            return item
        }

//        fun getNext5Emails(): MutableList<Item> {
//            var newEmails : MutableList<Item> = ArrayList()
//            for (i in 0..<newEmails.size) {
//                val email = Item(names, prices, urls)
//                newEmails.add(email)
//            }
//            return newEmails
//        }
    }
}