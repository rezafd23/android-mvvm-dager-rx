package com.example.android_mvvm_dager_rx.ui.list;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_mvvm_dager_rx.R;
import com.example.android_mvvm_dager_rx.base.BaseFragment;
import com.example.android_mvvm_dager_rx.data.model.Repo;
import com.example.android_mvvm_dager_rx.ui.detail.DetailsFragment;
import com.example.android_mvvm_dager_rx.ui.detail.DetailsViewModel;
import com.example.android_mvvm_dager_rx.util.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class ListFragment extends BaseFragment implements RepoSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(ListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new RepoListAdapter(viewModel, (LifecycleOwner) this, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();
    }

    @Override
    public void onRepoSelected(Repo repo) {
        DetailsViewModel detailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.setSelectedRepo(repo);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.screenContainer, new DetailsFragment())
                .addToBackStack(null).commit();
    }

    private void observableViewModel() {
        viewModel.getRepos().observe((LifecycleOwner) this, repos -> {
            if(repos != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe((LifecycleOwner) this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe((LifecycleOwner) this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
}
