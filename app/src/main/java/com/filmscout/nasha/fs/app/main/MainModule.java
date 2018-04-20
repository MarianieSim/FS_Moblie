package com.filmscout.nasha.fs.app.main;

import com.filmscout.nasha.fs.app.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private final MainContract.View mainView;

    MainModule(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Provides
    @ActivityScope
    MainContract.View provideMainView() {
        return mainView;
    }

}