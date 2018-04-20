package com.filmscout.nasha.fs.app.main;

import com.filmscout.nasha.fs.app.ActivityScope;
import com.filmscout.nasha.fs.app.AppComponent;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
interface MainComponent {

    void inject (MainActivity mainActivity);

}