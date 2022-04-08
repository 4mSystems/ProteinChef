package app.te.protein_chef.presentation.base.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.R
import app.te.protein_chef.databinding.PagingLoaderBinding

class HeaderAdapter : LoadStateAdapter<HeaderAdapter.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
    Log.e("onBindViewHolder", "onBindViewHolder: " + loadState.toString())
    if (loadState == LoadState.Loading) {
      //show progress view
      holder.itemLayoutBinding.progress.visibility = View.VISIBLE
    } else //hide the view
      holder.itemLayoutBinding.progress.visibility = View.GONE
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.paging_loader, parent, false)
    Log.e("onCreateViewHolder", "onCreateViewHolder: ")
    return ViewHolder(view)
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    lateinit var itemLayoutBinding: PagingLoaderBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }
  }
}