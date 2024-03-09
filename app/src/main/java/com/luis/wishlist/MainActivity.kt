package com.luis.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var items: MutableList<Item>
    private lateinit var loadButton: Button

    private lateinit var name: EditText
    private lateinit var price: EditText
    private lateinit var url: EditText

    private lateinit var itemsRv: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // all EditText with content in it
        name = findViewById(R.id.editText1)
        price = findViewById(R.id.editText2)
        url = findViewById(R.id.editText3)

        // Lookup the RecyclerView in activity layout
        itemsRv = findViewById<RecyclerView>(R.id.itemsRv)
        // Fetch the list of emails
        items = ItemFetcher.getEmails()
        // Create adapter passing in the list of emails
        itemAdapter = ItemAdapter(items){ position -> onItemLongClicked(position as Int) }
        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = itemAdapter
        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)

        // Set up ItemTouchHelper to handle swipe-to-delete
//        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(itemAdapter))
//        itemTouchHelper.attachToRecyclerView(itemsRv)

        loadButton = findViewById(R.id.button1)
        // Set initial state of submit button
        loadButton.isEnabled = false

        // Add TextWatcher to each EditText
        name.addTextChangedListener(watcher)
        price.addTextChangedListener(watcher)
        url.addTextChangedListener(watcher)

        loadButton.setOnClickListener {
            // Fetch next 5 emails and display in RecyclerView
            val newItem = Item(name.text.toString(), price.text.toString(), url.text.toString())
            // Add new emails to existing list of emails
            (items as MutableList<Item>).add(newItem)
            // Notify the adapter there's new emails so the RecyclerView layout is updated

            name.setText("")
            price.setText("")
            url.setText("")
            itemAdapter.notifyDataSetChanged()
        }
    }

    private fun onItemLongClicked(position: Int) {
        itemAdapter.removeItem(position)
        itemAdapter.notifyDataSetChanged()
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Not needed in this example
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Not needed in this example
        }

        override fun afterTextChanged(s: Editable?) {     s
            // Check if all three EditTexts have text
            val hasText1 = name.text.isNotEmpty()
            val hasText2 = price.text.isNotEmpty()
            val hasText3 = url.text.isNotEmpty()

            // Enable or disable the submit button based on the conditions
            loadButton.isEnabled = hasText1 && hasText2 && hasText3
        }
    }
}