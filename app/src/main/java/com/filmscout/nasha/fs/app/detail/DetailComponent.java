package com.filmscout.nasha.fs.app.detail;

import com.filmscout.nasha.fs.app.ActivityScope;
import com.filmscout.nasha.fs.app.AppComponent;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = DetailModule.class
)
interface DetailComponent {

    void inject(DetailActivity DetailActivity);

}