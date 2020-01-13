package kr.woochan.githubsearch.main.local

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.*
import kr.woochan.githubsearch.R
import kr.woochan.githubsearch.common.helper.ViewHolderHelper
import kr.woochan.githubsearch.data.local.RealmRepository
import kr.woochan.githubsearch.data.local.dao.User

class LocalRecyclerViewAdapter : RecyclerView.Adapter<ViewHolderHelper>() {

    var data = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
        return ViewHolderHelper(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {
        Glide.with(holder.iv_profile.context)
            .load(data[position].profileUrl)
            .into(holder.iv_profile)

        holder.tv_name.text = data[position].name

        if(position==0){
            holder.tv_header.visibility = View.VISIBLE
            holder.tv_header.text = data[position].name[0].toString()
        }else {
            val prevInitial = data[position - 1].name[0]
            val currentInitial = data[position].name[0]

            if(prevInitial == currentInitial) {
                holder.tv_header.visibility = View.GONE
            }else {
                holder.tv_header.visibility = View.VISIBLE
                holder.tv_header.text = currentInitial.toString()
            }
        }

        holder.ll_item.setOnClickListener {
            RealmRepository.removeUser(data[position].name)
        }
    }

    fun refreshData(list: List<User>) {
        this.data = list
        notifyDataSetChanged()
    }
}