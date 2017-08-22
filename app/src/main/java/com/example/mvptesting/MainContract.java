package com.example.mvptesting;


public interface MainContract {

    interface View {

        void onFetchDataStarted();

        void onFetchDataCompleted();

        void onFetchDataSuccess(CharactersResponseModel charactersResponseModel);

        void onFetchDataError(Throwable e);
    }

    interface Presenter {

        void loadData();

        void subscribe();

        void unsubscribe();

        void onDestroy();

    }
}

