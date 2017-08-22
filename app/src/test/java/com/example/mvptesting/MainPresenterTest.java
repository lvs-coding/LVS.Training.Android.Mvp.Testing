package com.example.mvptesting;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {
    @Mock
    private CharactersDataSource charactersDataSource;

    @Mock
    private MainContract.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {
        CharactersResponseModel charactersResponseModel = new CharactersResponseModel(0, null, null, null);

        // Whenever the method getCharacters() is called return the charactersResponseModel instance
        // declared
        when(charactersDataSource.getCharacters())
                .thenReturn(Observable.just(charactersResponseModel));

        MainPresenter mainPresenter = new MainPresenter(
                this.charactersDataSource,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view
        );

        mainPresenter.loadData();


        /*
        In brief, these are the view assertions that will be tested after loadData is called in this sequence:
            onFetchDataStarted method should be called once
            onFetchDataSuccess method should be called once and return the same exact model returned by CharactersDataSource.
            onFetchDataCompleted method should be called.
         */
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataSuccess(charactersResponseModel);
        inOrder.verify(view, times(1)).onFetchDataCompleted();
    }

    @Test
    public void fetchErrorShouldReturnErrorToView() {

        Exception exception = new Exception();

        when(charactersDataSource.getCharacters())
                .thenReturn(Observable.<CharactersResponseModel>error(exception));

        MainPresenter mainPresenter = new MainPresenter(
                this.charactersDataSource,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view
        );

        mainPresenter.loadData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataError(exception);
        verify(view, never()).onFetchDataCompleted();
    }


}
