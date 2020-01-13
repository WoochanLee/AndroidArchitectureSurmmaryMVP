package kr.woochan.githubsearch.main.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.*
import kr.woochan.githubsearch.R
import kr.woochan.githubsearch.common.helper.ViewHolderHelper
import kr.woochan.githubsearch.data.local.RealmRepository
import kr.woochan.githubsearch.data.remote.dto.GithubUsersResponse

class ApiRecyclerViewAdapter : RecyclerView.Adapter<ViewHolderHelper>() {

    private var data = listOf<GithubUsersResponse.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
        return ViewHolderHelper(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {
        holder.tv_name.text = data[position].login

        Glide.with(holder.iv_profile.context)
            .load(data[position].avatar_url)
            .into(holder.iv_profile)

        if (position == 0) {
            holder.tv_header.visibility = View.VISIBLE
            holder.tv_header.text = data[position].login[0].toString()
        } else {
            val prevInitial = data[position - 1].login[0]
            val currentInitial = data[position].login[0]

            if (prevInitial == currentInitial) {
                holder.tv_header.visibility = View.GONE
            } else {
                holder.tv_header.visibility = View.VISIBLE
                holder.tv_header.text = currentInitial.toString()
            }
        }

        holder.ll_item.setOnClickListener {
            if (RealmRepository.hasUser(data[position].login)) {
                RealmRepository.removeUser(data[position].login)
            } else {
                RealmRepository.insertUser(data[position])
            }
        }

        if (RealmRepository.hasUser(data[position].login)) {
            holder.tv_star.text = "★"
        } else {
            holder.tv_star.text = "☆"
        }
    }

    fun refreshData(data: List<GithubUsersResponse.Item>) {
        this.data = data
        notifyDataSetChanged()
    }
}