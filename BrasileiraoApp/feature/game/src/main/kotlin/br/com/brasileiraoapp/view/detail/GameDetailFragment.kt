package br.com.brasileiraoapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.brasileiraoapp.feature.game.R
import br.com.brasileiraoapp.feature.game.databinding.FragmentGameDetailBinding
import br.com.brasileiraoapp.imageloader.loadImageForUrl
import br.com.brasileiraoapp.view.hide
import br.com.brasileiraoapp.view.show
import br.com.brasileiraoapp.viewmodel.detail.GameDetailViewAction
import br.com.brasileiraoapp.viewmodel.detail.GameDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class GameDetailFragment : Fragment() {

    private val args: GameDetailFragmentArgs by navArgs()
    private val bidAdapter by lazy { GameDetailAdapter() }
    private lateinit var binding: FragmentGameDetailBinding

    private val viewModel by viewModel<GameDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGameDetailBinding.inflate(inflater, container, false).apply {
            binding = this
            initView()
            observeChangesInViewModel()
        }.root
    }

    private fun observeChangesInViewModel() {
        viewModel.viewState.gameBids.observe(viewLifecycleOwner) {
            bidAdapter.setData(
                it ?: listOf()
            )
        }

        viewModel.viewState.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.loading.show()
            } else {
                binding.loading.hide()
            }
        }

        viewModel.dispatchViewAction(GameDetailViewAction.GetBids(args.game.id))
    }

    private fun initView() {
        binding.gameModel = args.game
        binding.bidList.adapter = bidAdapter

        binding.teamHome.loadImageForUrl(
            args.game.teamHome.shieldUrl,
            R.drawable.shield_team_default
        )

        binding.teamAway.loadImageForUrl(
            args.game.teamAway.shieldUrl,
            R.drawable.shield_team_default
        )

        binding.backArrow.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}