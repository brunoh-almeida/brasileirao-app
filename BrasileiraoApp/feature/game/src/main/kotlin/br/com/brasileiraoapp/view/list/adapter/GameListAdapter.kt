package br.com.brasileiraoapp.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.brasileiraoapp.feature.game.R
import br.com.brasileiraoapp.feature.game.databinding.AdapterGameListBinding
import br.com.brasileiraoapp.imageloader.loadImageForUrl
import br.com.brasileiraoapp.presentation.model.GameModel

class GameListAdapter(private val onItemClick: (GameModel) -> Unit) :
    RecyclerView.Adapter<GameListViewHolder>() {

    private var games: MutableList<GameModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val binding =
            AdapterGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameListViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int =
        games.size

    fun setData(newGames: List<GameModel>) {
        val diffResult = DiffUtil.calculateDiff(GameListDiffUtil(this.games, newGames))
        games.clear()
        games.addAll(newGames)
        diffResult.dispatchUpdatesTo(this)
    }

}

class GameListViewHolder(
    private val binding: AdapterGameListBinding,
    private val onItemClick: (GameModel) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameModel: GameModel) {
        binding.teamAway.loadImageForUrl(
            gameModel.teamAway.shieldUrl,
            R.drawable.shield_team_default
        )
        binding.teamHome.loadImageForUrl(
            gameModel.teamHome.shieldUrl,
            R.drawable.shield_team_default
        )

        binding.gameModel = gameModel
        binding.root.setOnClickListener {
            onItemClick(gameModel)
        }
    }
}

class GameListDiffUtil(private val oldList: List<GameModel>, private val newList: List<GameModel>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]


}