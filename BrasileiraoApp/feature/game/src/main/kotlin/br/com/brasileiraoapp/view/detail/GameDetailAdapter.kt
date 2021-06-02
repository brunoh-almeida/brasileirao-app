package br.com.brasileiraoapp.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.brasileiraoapp.feature.game.R
import br.com.brasileiraoapp.feature.game.databinding.AdapterGameDetailBinding
import br.com.brasileiraoapp.imageloader.loadImageForUrl
import br.com.brasileiraoapp.presentation.model.BidModel
import br.com.brasileiraoapp.presentation.model.BidType
import br.com.brasileiraoapp.view.invisible
import br.com.brasileiraoapp.view.show

class GameDetailAdapter : RecyclerView.Adapter<GameDetailViewHolder>() {

    private val bids: MutableList<BidModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameDetailViewHolder {
        val binding =
            AdapterGameDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameDetailViewHolder, position: Int) {
        holder.bind(bids[position])
    }

    override fun getItemCount(): Int = bids.size

    fun setData(newBids: List<BidModel>) {
        val diffResult = DiffUtil.calculateDiff(GameAdapterDiffUtil(bids, newBids))
        bids.clear()
        bids.addAll(newBids)
        diffResult.dispatchUpdatesTo(this)
    }
}

class GameDetailViewHolder(private val binding: AdapterGameDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(bid: BidModel) {
        when (bid.type) {
            BidType.INIT -> bidInitBid(bid)
            BidType.END -> bidEndBid(bid)
            BidType.NORMAL -> bidNormalBid(bid)
        }
    }

    private fun bidNormalBid(bid: BidModel) {
        binding.team.loadImageForUrl(bid.teamShieldUrl ?: "", R.drawable.shield_team_default)
        binding.time.text = bid.timeOfGame.toString()
        binding.description.text = bid.description
        binding.minutes.show()
        binding.time.show()
        binding.team.show()
    }

    private fun bidEndBid(bid: BidModel) {
        binding.time.text = bid.timeOfGame.toString()
        binding.description.text = bid.description
        binding.minutes.show()
        binding.time.show()
        binding.team.invisible()
    }

    private fun bidInitBid(bid: BidModel) {
        binding.description.text = bid.description
        binding.minutes.invisible()
        binding.time.invisible()
        binding.team.invisible()
    }
}

class GameAdapterDiffUtil(
    private val oldList: List<BidModel>,
    private val newList: List<BidModel>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].timeOfGame == newList[newItemPosition].timeOfGame

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]


}