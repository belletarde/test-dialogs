package com.app.cat.kevin.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dialog_item.*

class CustomAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    private var context: Context? = null
    private var dietList: MutableList<CustomAdapterModel> = ArrayList()
    private var onImageClick : OnImageClick? = null

    interface OnImageClick {
        fun onImageClickListener(title: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return DietItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dialog_item, parent, false), context!!, onImageClick!!)
    }

    fun setListView(dietList: List<CustomAdapterModel>, context: Context, onImageClick: OnImageClick) {
        this.dietList.addAll(dietList)
        this.context = context
        notifyDataSetChanged()
        this.onImageClick = onImageClick
    }

    override fun getItemCount(): Int {
        return dietList.size
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as DietItemViewHolder).bind(dietList[position], position)
    }

    class DietItemViewHolder(itemView: View, context: Context, onImageClick: OnImageClick) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView = itemView
        private val adapterContext = context
        private val click = onImageClick

        fun bind(items: CustomAdapterModel, position: Int) {
            with(items) {
                ic_list.setImageDrawable(adapterContext.resources.getDrawable(img))
                txt_list.text = txt
                item_custom_click.setOnClickListener { click.onImageClickListener(txt, position) }
            }
        }
    }

}