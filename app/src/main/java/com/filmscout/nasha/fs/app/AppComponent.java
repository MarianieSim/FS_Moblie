package com.filmscout.nasha.fs.app;

import android.app.Application;

import com.filmscout.nasha.fs.api.ApiModule;
import com.filmscout.nasha.fs.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class
        }
)
public interface AppComponent {

    Application application();

    ApiService apiService();

    void inject(App app);

}
