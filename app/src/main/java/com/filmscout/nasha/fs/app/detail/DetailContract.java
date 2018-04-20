package com.filmscout.nasha.fs.app.detail;

import com.filmscout.nasha.fs.api.model.Images;
import com.filmscout.nasha.fs.api.model.Movie;

public interface DetailContract {

    interface View {

        void showLoading();

        void showContent(Movie movie);

        void showError();

        void onConfigurationSet(Images images);

    }

    interface Presenter {

        void start(int movieId);

        void finish();

    }

}

