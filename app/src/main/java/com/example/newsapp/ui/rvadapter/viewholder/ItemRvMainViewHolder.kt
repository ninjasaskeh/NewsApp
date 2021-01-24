package com.example.newsapp.ui.rvadapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.size.Scale
import com.idn.fahru.beritaapp.databinding.ItemRvMainBinding
import com.example.newsapp.helpers.toRelativeDate
import com.example.newsapp.model.remote.ArticlesItem
import java.lang.System.load

/**
 * Created by Imam Fahrur Rofi on 01/06/2020.
 */
class ItemRvMainViewHolder(private val itemRvMainBinding: ItemRvMainBinding) :
    RecyclerView.ViewHolder(itemRvMainBinding.root) {
    fun bind(item: ArticlesItem) {
        itemRvMainBinding.run {
            textTitle.text = item.title
            imageRvMain.apply {
                load(item.urlToImage) {
                    scale(Scale.FILL)
                }
                contentDescription = item.title
            }
            item.publishedAt?.let {
                textDate.text = it.toRelativeDate
            }
        }
    }
}