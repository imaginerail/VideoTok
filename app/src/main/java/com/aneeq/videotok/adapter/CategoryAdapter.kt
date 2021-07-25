package com.aneeq.videotok.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aneeq.videotok.R
import com.aneeq.videotok.model.Categories

class CategoryAdapter(val context: Context, val categoryList: ArrayList<Categories>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCategory: TextView = view.findViewById(R.id.imgCategory)
        val txtCategory: TextView = view.findViewById(R.id.txtCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_categories_single_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categImage = arrayListOf(
            "https://www.apptunix.com/blog/wp-content/uploads/sites/3/2020/04/online-food-delivery-industry.jpg",
            "https://static.timesprime.com/2x/gp-mobile.jpg?v=9","",
            "https://2.bp.blogspot.com/_7ZWRGFsPCAY/TF_XBxDlhDI/AAAAAAAAAA0/s5s3Q3eW3r8/s400/cox7.jpg"
        )
        val categ = categoryList[position]
        holder.txtCategory.text = categ.category
    }
}