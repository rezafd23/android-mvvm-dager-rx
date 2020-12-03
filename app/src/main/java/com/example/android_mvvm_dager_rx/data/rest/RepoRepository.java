package com.example.android_mvvm_dager_rx.data.rest;

import com.example.android_mvvm_dager_rx.data.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RepoRepository {
    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Repo>> getRepositories() {
        return repoService.getRepositories();
    }

    public Single<Repo> getRepo(String owner, String name) {
        return repoService.getRepo(owner, name);
    }
}
