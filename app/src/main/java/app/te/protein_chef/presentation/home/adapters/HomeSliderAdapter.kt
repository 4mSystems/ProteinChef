package app.te.protein_chef.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.presentation.home.eventListener.HomeEventListener
import com.smarteist.autoimageslider.SliderViewAdapter
import app.te.protein_chef.presentation.home.ui_state.SliderUiItemState
import app.te.protein_chef.R
import app.te.protein_chef.databinding.HomeSliderItemBinding
import org.jetbrains.annotations.NotNull

class HomeSliderAdapter(val eventListener: HomeEventListener) :
  SliderViewAdapter<HomeSliderAdapter.SliderAdapterVH>() {
  var sliderList: ArrayList<SliderUiItemState> = ArrayList()
  override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
    val view =
      LayoutInflater.from(parent?.context).inflate(R.layout.home_slider_item, parent, false)
    return SliderAdapterVH(view)

  }

  override fun onBindViewHolder(holder: SliderAdapterVH, position: Int) {
    val data = sliderList[position]
    holder.setModel(data, eventListener)

  }

  override fun getCount(): Int {
    return sliderList.size
  }

  fun update(@NotNull list: List<SliderUiItemState>) {
    sliderList.clear()
    sliderList.addAll(list)
    notifyDataSetChanged()
  }

  class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
    private lateinit var itemLayoutBinding: HomeSliderItemBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(slider: SliderUiItemState, eventListener: HomeEventListener) {
      itemLayoutBinding.eventListener = eventListener
      itemLayoutBinding.uiState = slider
    }
  }
}