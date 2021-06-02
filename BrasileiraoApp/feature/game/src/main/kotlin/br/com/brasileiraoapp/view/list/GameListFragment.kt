package br.com.brasileiraoapp.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.brasileiraoapp.feature.game.R
import br.com.brasileiraoapp.feature.game.databinding.FragmentGameListBinding
import br.com.brasileiraoapp.presentation.model.GameViewResult
import br.com.brasileiraoapp.view.isVisible
import br.com.brasileiraoapp.view.list.adapter.GameListAdapter
import br.com.brasileiraoapp.viewmodel.list.GameViewAction
import br.com.brasileiraoapp.viewmodel.list.GameViewModel
import br.com.brasileiraoapp.viewmodel.list.GameViewState
import org.koin.android.viewmodel.ext.android.viewModel

class GameListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentGameListBinding
    private lateinit var gameListAdapter: GameListAdapter
    private val gameViewModel by viewModel<GameViewModel>()
    private var round = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGameListBinding.inflate(inflater, container, false).apply {
            binding = this
            initViews()
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChangesViewModel()
    }

    private fun observeChangesViewModel() {
        gameViewModel.viewState.gameResult.observe(viewLifecycleOwner) { result ->
            handleGameResult(result)
        }

        gameViewModel.viewState.listState?.let {
            handleGameResult(gameViewModel.viewState.gameResult.value)
        } ?: run {
            gameViewModel.dispatchViewAction(GameViewAction.OnRoundClick(round))
        }

        gameViewModel.viewState.round.observe(viewLifecycleOwner) { roundResult ->
            round = roundResult
            binding.round.text = getString(R.string.round, roundResult)
        }

        gameViewModel.viewState.shouldShowLoading.observe(viewLifecycleOwner) {
            binding.loading.isVisible(it)
        }

        gameViewModel.viewState.error.observe(viewLifecycleOwner) {
            handleError(it?.msg ?: getString(R.string.error_generic))
        }

        gameViewModel.viewState.action.observe(viewLifecycleOwner) {
            when (it) {
                is GameViewState.Action.OpenGameDetail -> {
                    findNavController().navigate(
                        GameListFragmentDirections
                            .actionGameListFragmentToGameDetailFragment(it.game)
                    )
                }

                GameViewState.Action.SwipeLoadFinished -> {
                    binding.refreshLayout.isRefreshing = false
                }
            }
        }

        gameViewModel.dispatchViewAction(GameViewAction.Init)
    }

    private fun handleGameResult(result: GameViewResult?) {
        when (result) {
            is GameViewResult.Success -> gameListAdapter.setData(result.content)
            is GameViewResult.Error -> {
                handleError(result)
            }
        }
    }

    private fun handleError(error: GameViewResult.Error) {
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        gameListAdapter = GameListAdapter(onItemClick = {
            gameViewModel.dispatchViewAction(GameViewAction.OnGameListClick(it))
        })
        binding.gameList.adapter = gameListAdapter
        binding.refreshLayout.setOnRefreshListener(this)
        binding.fowardRound.setOnClickListener {
            gameViewModel.dispatchViewAction(GameViewAction.OnRoundClick(round + 1))
        }

        binding.backRound.setOnClickListener {
            if (round > 1)
                gameViewModel.dispatchViewAction(GameViewAction.OnRoundClick(round - 1))
            else {
                handleError(getString(R.string.round_minimun))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        gameViewModel.viewState.listState = binding.gameList.layoutManager?.onSaveInstanceState()
    }

    override fun onRefresh() {
        gameViewModel.dispatchViewAction(GameViewAction.OnSwipeRefresh)
    }

}