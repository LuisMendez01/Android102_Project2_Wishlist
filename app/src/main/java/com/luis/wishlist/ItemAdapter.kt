package com.luis.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: MutableList<Item>, private val onItemLongClickListener: (Int) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val name: TextView
        val price: TextView
        val url: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            name = itemView.findViewById(R.id.name)
            price = itemView.findViewById(R.id.price)
            url = itemView.findViewById(R.id.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val email = items.get(position)
        // Set item views based on views and data model
        holder.name.text = email.name
        holder.price.text = email.price
        holder.url.text = email.url

        // Set a long-press listener on the item view
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener.invoke(position)
            true  // Return true to indicate that the long click event is consumed
        }
    }
}

//class SwipeToDeleteCallback(private val adapter: ItemAdapter) : ItemTouchHelper.SimpleCallback(
//    0,
//    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//) {
//    override fun onMove(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        target: RecyclerView.ViewHolder
//    ): Boolean {
//        // Not needed for this example
//        return false
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        val position = viewHolder.adapterPosition
//        adapter.removeItem(position)
//    }
//}