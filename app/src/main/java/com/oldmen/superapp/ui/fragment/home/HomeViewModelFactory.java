package com.oldmen.superapp.ui.fragment.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.oldmen.superapp.db.handler.SuperDatabase;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private Context context;
    private SuperDatabase superDatabase;

    HomeViewModelFactory(Context context, SuperDatabase superDatabase) {
        this.context = context;
        this.superDatabase = superDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(context, superDatabase);
    }
}